//
//  CHSubmitOrderViewController.m
//  shenbianapp
//
//  Created by book on 2017/11/6.
//  Copyright © 2017 helinkeji. All rights reserved.
//

#import "CHSubmitOrderViewController.h"
#import "CHOrderModel.h"
#import "CHInputAddressViewController.h"
//#import <IQTextView.h>
#import <WXApi.h>
#import "CHMyOrdersViewController.h"
#import "CHSubmitOrderModel.h"
#import "CHSubmitOrderTableViewCell.h"

@interface CHSubmitOrderViewController ()<UITableViewDelegate,UITableViewDataSource>
@property(nonatomic,strong)UITableView *tableView;
@property(nonatomic,copy)NSArray *dataAray;
@property(nonatomic,strong)UIButton *payButton;
@property(nonatomic,strong)CHOrderModel *orderModel;
@property(nonatomic,strong)CHSubmitOrderModel *submitModel;
@property(nonatomic,assign) float totalFee;
@end

@implementation CHSubmitOrderViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = @"提交订单";
    
}

-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    self.tabBarController.tabBar.hidden = YES;
    [IQKeyboardManager sharedManager].enable = YES;
    
    
}

-(void)viewWillDisappear:(BOOL)animated{
    [super viewWillDisappear:animated];
    self.tabBarController.tabBar.hidden = NO;
    [IQKeyboardManager sharedManager].enable = NO;
    
}
- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void)bindViewControllerModel{
    self.submitModel = [CHSubmitOrderModel new];
    self.orderModel = [CHOrderModel new];
    
    self.dataAray = [self.orderList arrayByAddingObjectsFromArray:@[@[@"添加位置、联系人",@"添加时间",@"备注"],@[@"微信支付"]]];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(WXPaySuccess) name:kCHNotificationWXPaySuccess object:nil];
    
}

- (void)WXPaySuccess{
    CHMyOrdersViewController *orderlist = [CHMyOrdersViewController new];
    [self.navigationController pushViewController:orderlist animated:YES];
}

-(void)setupViews{
    
    [self.view addSubview:self.tableView];
    [self.tableView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.view).offset(64);
        make.left.right.equalTo(self.view);
        make.bottom.equalTo(self.view).offset(-55);
    }];
    
    [self.view addSubview:self.payButton];
    [self.payButton mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.bottom.equalTo(self.view);
        make.height.mas_equalTo(55);
    }];
}

-(UIButton *)payButton{
    
    if (_payButton == nil) {
        _payButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        _payButton.backgroundColor = [UIColor colorWithHexString:@"#ff7f7a"];
        [_payButton setTitle:[NSString stringWithFormat:@"￥ %.2f 支付",self.totalFee] forState:(UIControlStateNormal)];
        [_payButton addTarget:self action:@selector(clickPayButton) forControlEvents:(UIControlEventTouchUpInside)];
    }
    return _payButton;
}

-(void)clickPayButton{
    __block NSString *orderId = @"";
    NSString *token = [[NSUserDefaults standardUserDefaults] objectForKey:@"toekn"];
    NSString *orderDetails = [@{@"serviceId":@"17",@"orderQuantity":@"1"} jsonStringEncoded];
    NSDictionary *orderDic = @{@"orderDetails":orderDetails,@"note":@"暂无",@"createTime":@"2017-12-11 18:00:00",@"paymentType":@"1",@"address":@"",@"token":token};
    RACSignal *addOrderSignal = [self.submitModel.addOrderCommand execute:orderDic];
    [addOrderSignal subscribeNext:^(id x) {
        
        orderId = [x objectForKey:@"data"];
        if (orderId==nil) {
            UIAlertView *alertView = [[UIAlertView alloc]initWithTitle:@"温馨提示" message:[x objectForKey:@"error"] delegate:nil cancelButtonTitle:nil otherButtonTitles:@"知晓", nil];
            [alertView show];
            return ;
        }
        RACSignal *signal = [self.submitModel.payCommand execute:@{@"orderId":orderId,@"token":token}];
        [signal subscribeNext:^(id x) {
            
            NSDictionary *tempDic = [x objectForKey:@"data"];
            PayReq *request = [[PayReq alloc] init];
            request.partnerId = [tempDic objectForKey:@"partnerid"];
            request.prepayId= [tempDic objectForKey:@"prepayid"];
            request.package = @"Sign=WXPay";
            request.nonceStr= [tempDic objectForKey:@"noncestr"];
            request.timeStamp =  [[tempDic objectForKey:@"timestamp"] intValue];
            
            request.sign= [tempDic objectForKey:@"sign"];
            [WXApi sendReq:request];
            
        } error:^(NSError *error) {
            NSLog(@"pay %@",error);
        }];
        
    } error:^(NSError *error) {
        NSLog(@"addOrder error: %@",error);
    }];
    
}


-(UITableView *)tableView{
    if (_tableView == nil) {
        _tableView = [[UITableView alloc]initWithFrame:(CGRectZero) style:(UITableViewStyleGrouped)];
        _tableView.delegate = self;
        _tableView.dataSource = self;
        _tableView.tableFooterView = [UIView new];
        [_tableView registerClass:[CHSubmitOrderTableViewCell class] forCellReuseIdentifier:@"orderCell"];
        _tableView.backgroundColor = [UIColor colorWithHexString:@"#f6f6f6"];
        _tableView.separatorColor = [UIColor colorWithHexString:@"#ebebeb"];
    }
    return _tableView;
}


-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    
    return self.dataAray.count;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    if (section >= self.dataAray.count - 2) {
        return [self.dataAray[section] count];
    }
    NSDictionary *storeList = self.dataAray[section];
    NSDictionary *orderList = [storeList objectForKey:@"carts"];
    return orderList.count;
}
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    CHSubmitOrderTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"orderCell" forIndexPath:indexPath];
    cell.dataArray = self.dataAray;
    cell.indexPath = indexPath;

    return cell;
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    if (indexPath.section == self.dataAray.count - 2 && indexPath.row == 2) {
        return 75;
    }
    return 56;
}

-(UIView*)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section{
    
    UIView *contentView = [[UIView alloc]initWithFrame:(CGRectMake(0, 0, kScreenWidth, 85))];
    contentView.layer.borderWidth = 0.3;
    contentView.layer.borderColor = [UIColor colorWithHexString:@"#ebebeb"].CGColor;
    contentView.backgroundColor = [UIColor whiteColor];
    UIImageView *headImageView = [[UIImageView alloc]initWithFrame:(CGRectMake(15, 15, 50, 50))];
    headImageView.image = [UIImage imageNamed:@"default_headImage"];
    headImageView.layer.cornerRadius = 25;
    headImageView.clipsToBounds = YES;
    [contentView addSubview:headImageView];
    
    UILabel *namelabel = [UILabel new];
    NSDictionary *storeList = self.dataAray[section];
    
    namelabel.text = [NSString stringWithFormat:@"%@",[storeList objectForKey:@"shopName"]];
    namelabel.textColor = [UIColor colorWithHexColor:@"#2d333a"];
    namelabel.font = [UIFont systemFontOfSize:15];
    [contentView addSubview:namelabel];
    [namelabel mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(headImageView.mas_right).offset(15);
        make.centerY.equalTo(headImageView);
        make.width.mas_equalTo(120);
        make.height.mas_equalTo(20);
    }];
    
    return contentView;
}

-(CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section{
    
    if (section < self.dataAray.count - 2) {

        return 85;
    }
    return 0;
}

-(CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section{
    return 1;
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    
    if (indexPath.section == self.dataAray.count - 2 ) {
        if (indexPath.row == 0) {
            CHInputAddressViewController *input = [CHInputAddressViewController new];
            [self.navigationController pushViewController:input animated:YES];
        } else if(indexPath.row == 1){
            UIDatePicker *datePiker = [[UIDatePicker alloc]init];
            datePiker.datePickerMode = UIDatePickerModeDateAndTime;
            [self.view addSubview:datePiker];
            [datePiker mas_makeConstraints:^(MASConstraintMaker *make) {
                make.height.mas_equalTo(200);
                make.left.right.bottom.equalTo(self.view);
            }];
            
            UIButton *donebutton = [UIButton buttonWithType:(UIButtonTypeCustom)];
            [donebutton setTitle:@"完成" forState:(UIControlStateNormal)];
            [donebutton addTarget:datePiker action:@selector(removeFromSuperview) forControlEvents:(UIControlEventTouchUpInside)];
            donebutton.frame = CGRectMake(0, 0, 40, 30);
            [datePiker.inputAccessoryView addSubview:donebutton];
            
        }
    }
    
    if (indexPath.section == self.dataAray.count - 1) {
        UIAlertView *alertView = [[UIAlertView alloc]initWithTitle:@"温馨提示" message:@"暂不支持其他支付" delegate:nil cancelButtonTitle:nil otherButtonTitles:@"知晓", nil];
        [alertView show];
    }
    
   
}

@end
