//
//  CHShoppingCartViewController.m
//  shenbianapp
//
//  Created by book on 2017/11/6.
//  Copyright © 2017 helinkeji. All rights reserved.
//

#import "CHShoppingCartViewController.h"
#import "CHShoppingCartTableViewCell.h"
#import "CHSubmitOrderViewController.h"
#import "CHServiceDetailsViewController.h"
#import "CHShopingModel.h"
#import "CHShopingCartViewModel.h"
@interface CHShoppingCartViewController ()<UITableViewDelegate,UITableViewDataSource>
@property(nonatomic,strong)UITableView *tableView;
@property(nonatomic,strong)UIButton *makeOrderButton;
@property(nonatomic,strong)NSMutableArray *orderModelList;
@property(nonatomic,strong)NSMutableArray *shopingModelList;
@property(nonatomic,assign)NSUInteger totalCount;
@end

@implementation CHShoppingCartViewController
@dynamic viewCModel;
- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = @"购物车";
    self.rightButton.hidden = NO;
    [self.rightButton setImage:[UIImage imageNamed:@"gwc_sc"] forState:(UIControlStateNormal)];
}

-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    self.tabBarController.tabBar.hidden = YES;
}

-(void)viewWillDisappear:(BOOL)animated{
    [super viewWillDisappear:animated];
    self.tabBarController.tabBar.hidden = NO;
    
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void)bindViewControllerModel{
    self.viewCModel = [CHShopingCartViewModel new];
    NSString *token = [[NSUserDefaults standardUserDefaults] objectForKey:@"toekn"];
    RACSignal *signal = [self.viewCModel.loadPagedata execute:@{@"token":token}];
    [signal subscribeNext:^(id x) {
        NSLog(@"ssss:%@",x);
        self.shopingModelList = [NSMutableArray arrayWithArray:[x objectForKey:@"data"]];
        [self.tableView reloadData];
        
       
        NSString *title = [NSString stringWithFormat:@"合计 %ld 下单",self.totalCount];
        [self.makeOrderButton setTitle:title forState:(UIControlStateNormal)];
    } error:^(NSError *error) {
        NSLog(@"error:%@",error);
    }];
    
    
}

-(void)setupViews{
    
    [self.view addSubview:self.tableView];
    [self.tableView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.bottom.equalTo(self.view);
        make.bottom.equalTo(self.view).offset(-55);
        make.top.equalTo(self.view).offset(64);
    }];
    
    [self.view addSubview:self.makeOrderButton];
    [self.makeOrderButton mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.bottom.equalTo(self.view);
        make.height.mas_equalTo(55);
    }];
    
}
-(UITableView *)tableView{
    
    if (_tableView == nil) {
        _tableView = [UITableView new];
        _tableView.delegate = self;
        _tableView.dataSource = self;
        [_tableView registerClass:[CHShoppingCartTableViewCell class] forCellReuseIdentifier:@"cartCell"];
        _tableView.tableFooterView = [UIView new];
        _tableView.backgroundColor = [UIColor colorWithHexColor:@"#f6f6f6"];
        _tableView.separatorColor = [UIColor colorWithHexColor:@"#ebebeb"];
    }
    return  _tableView;
}

-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    
    return  self.shopingModelList.count;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    NSDictionary *storeDic =  self.shopingModelList[section];

    if (storeDic.count>0) {
        NSArray *orderList = [storeDic objectForKey:@"carts"];
        self.totalCount += orderList.count;

        return [orderList count];
    }
    return 0;
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    CHShoppingCartTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"cartCell" forIndexPath:indexPath];
    NSDictionary *storeDic =  self.shopingModelList[indexPath.section];
    NSArray *orderList = [storeDic objectForKey:@"carts"];
    cell.orderDic = orderList[indexPath.row];
    return cell;
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    
    return 106;
}

-(CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section{
    return 85;
}
-(UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section{
    
    NSDictionary *dict = self.shopingModelList[section];
    UIView *contentView = [[UIView alloc]initWithFrame:(CGRectMake(0, 0, kScreenWidth, 85))];
    contentView.layer.borderWidth = 0.3;
    contentView.layer.borderColor = [UIColor colorWithHexColor:@"#ebebeb"].CGColor;
    contentView.backgroundColor = [UIColor whiteColor];
    UIImageView *headImageView = [[UIImageView alloc]initWithFrame:(CGRectMake(15, 15, 50, 50))];
    [headImageView setImageWithURL:[NSURL URLWithString:[dict objectForKey:@"userIcon"]] placeholder:[UIImage imageNamed:@"default_headImage"]];
    headImageView.layer.cornerRadius = 25;
    headImageView.clipsToBounds = YES;
    headImageView.contentMode = UIViewContentModeScaleAspectFit;
    [contentView addSubview:headImageView];
    
    UILabel *namelabel = [UILabel new];
    namelabel.text = [dict objectForKey:@"shopName"];
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


-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    CHServiceDetailsViewController *serviceDetail = [CHServiceDetailsViewController new];
    NSDictionary *storeDic =  self.shopingModelList[indexPath.section];
    NSArray *orderList = [storeDic objectForKey:@"carts"];
    NSDictionary *orderDic = orderList[indexPath.row];
    NSString *serviceId = [orderDic objectForKey:@"serviceId"];
    serviceDetail.serviceId = serviceId;
    [self.navigationController pushViewController:serviceDetail animated:YES];
    
}

- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath
{
    // 删除模型
    
    NSMutableDictionary *storeDic = [NSMutableDictionary dictionaryWithDictionary:self.shopingModelList[indexPath.section]];
    NSMutableArray *orderList = [NSMutableArray arrayWithArray:[storeDic objectForKey:@"carts"]];
    [orderList removeObjectAtIndex:indexPath.row];
    [storeDic setObject:orderList forKey:@"carts"];
    
    self.shopingModelList[indexPath.section] = orderList;
    // 刷新
    [tableView beginUpdates];
    [tableView deleteRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationLeft];
    [tableView endUpdates];
    
    NSString *title = [NSString stringWithFormat:@"合计 %ld 下单",self.totalCount--];
    [self.makeOrderButton setTitle:title forState:(UIControlStateNormal)];
}

-(void)clickRightButton{
    if (self.tableView.editing) {
        [self.tableView setEditing:NO];
    } else {
        [self.tableView setEditing:YES];
    }
}

-(UIButton *)makeOrderButton{
    
    if (_makeOrderButton == nil) {
        
        _makeOrderButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        NSString *title = [NSString stringWithFormat:@"合计 %ld 下单",self.shopingModelList.count];
        [_makeOrderButton setTitle:title forState:(UIControlStateNormal)];
        _makeOrderButton.backgroundColor = [UIColor colorWithHexColor:@"#009698"];
        [_makeOrderButton addTarget:self action:@selector(clickMakeOrderButton) forControlEvents:(UIControlEventTouchUpInside)];
    }
    
    return _makeOrderButton;
}

- (void)clickMakeOrderButton{
    
    
    if (self.totalCount > 0) {
        
        CHSubmitOrderViewController *submitOrder = [CHSubmitOrderViewController new];
        submitOrder.orderList = self.shopingModelList;
        [self.navigationController pushViewController:submitOrder animated:YES];
    }
}

@end
