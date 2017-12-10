//
//  CHSubmitOrderViewController.m
//  shenbianapp
//
//  Created by book on 2017/11/6.
//  Copyright © 2017 helinkeji. All rights reserved.
//

#import "CHSubmitOrderViewController.h"

#import "CHInputAddressViewController.h"
#import <IQTextView.h>
@interface CHSubmitOrderViewController ()<UITableViewDelegate,UITableViewDataSource>
@property(nonatomic,strong)UITableView *tableView;
@property(nonatomic,copy)NSArray *dataAray;
@property(nonatomic,strong)UIButton *payButton;
@end

@implementation CHSubmitOrderViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = @"提交订单";
    [IQKeyboardManager sharedManager].enable = YES;
    [IQKeyboardManager sharedManager].toolbarDoneBarButtonItemText = @"完成";
    [IQKeyboardManager sharedManager].enableAutoToolbar = YES;
}

-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    self.tabBarController.tabBar.hidden = YES;
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
    
    self.dataAray = @[@[@{@"serviceTitle":@"服务名称",@"price":@"122",@"num":@"1"},],@[@"添加位置、联系人",@"添加时间",@"备注"],@[@"支付宝支付"]];
    
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
        [_payButton setTitle:@"￥ 1000 支付" forState:(UIControlStateNormal)];
    }
    return _payButton;
}

-(UITableView *)tableView{
    if (_tableView == nil) {
        _tableView = [[UITableView alloc]initWithFrame:(CGRectZero) style:(UITableViewStyleGrouped)];
        _tableView.delegate = self;
        _tableView.dataSource = self;
        _tableView.tableFooterView = [UIView new];
        [_tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:@"orderCell"];
        _tableView.backgroundColor = [UIColor colorWithHexString:@"#f6f6f6"];
        _tableView.separatorColor = [UIColor colorWithHexString:@"#ebebeb"];
    }
    return _tableView;
}


-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    
    return self.dataAray.count;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return [self.dataAray[section] count];
}
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"orderCell" forIndexPath:indexPath];
    cell.textLabel.font = [UIFont systemFontOfSize:15];
    cell.textLabel.textColor = [UIColor colorWithHexString:@"#2d333a"];
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    switch (indexPath.section) {
        case 0:
        {
            cell.textLabel.text = [self.dataAray[0][indexPath.row] objectForKey:@"serviceTitle"];
            
            UILabel *priceLabel = [UILabel new];
            priceLabel.font = [UIFont systemFontOfSize:15];
            priceLabel.textColor = [UIColor colorWithHexString:@"#2d333a"];
            priceLabel.text = [NSString stringWithFormat:@"￥%@",[self.dataAray[0][indexPath.row] objectForKey:@"price"]];
            [cell.contentView addSubview:priceLabel];
            
            [priceLabel mas_makeConstraints:^(MASConstraintMaker *make) {
                make.centerY.equalTo(cell.contentView);
                make.right.mas_equalTo(cell.contentView).offset(-15);
                make.width.mas_equalTo(60);
                make.height.mas_equalTo(20);
            }];
            
            UILabel *numLabel = [UILabel new];
            numLabel.font = [UIFont systemFontOfSize:15];
            numLabel.textColor = [UIColor colorWithHexString:@"#2d333a"];
            numLabel.text = [NSString stringWithFormat:@"x  %@",[self.dataAray[0][indexPath.row] objectForKey:@"num"]];
            
            [cell.contentView addSubview:numLabel];
            [numLabel mas_makeConstraints:^(MASConstraintMaker *make) {
                make.centerY.mas_equalTo(cell.contentView);
                make.right.equalTo(priceLabel.mas_left).offset(-15);
                make.width.mas_equalTo(60);
                make.height.mas_equalTo(20);
            }];
            
            
        } break;
        case 1:
        {
            if (indexPath.row == 0 || indexPath.row == 1) {
                cell.accessoryType = UITableViewCellAccessoryDisclosureIndicator;
                cell.textLabel.text = self.dataAray[indexPath.section][indexPath.row];

            } else{
                UILabel *label = [UILabel new];
                label.text = @"备注";
                label.textColor = [UIColor colorWithHexString:@"#2d333a"];
                label.font = [UIFont systemFontOfSize:15];
                [cell.contentView addSubview:label];
                [label mas_makeConstraints:^(MASConstraintMaker *make) {
                    make.left.equalTo(cell.contentView).offset(15);
                    make.top.equalTo(cell.contentView).offset(10);
                    make.width.mas_equalTo(40);
                    make.height.mas_equalTo(20);
                }];
                cell.accessoryType = UITableViewCellAccessoryNone;

                IQTextView *textView = [[IQTextView alloc]init];
                textView.placeholder = @"请输入其他要求";
                textView.font = [UIFont systemFontOfSize:13];
                [cell.contentView addSubview:textView];
                [textView mas_makeConstraints:^(MASConstraintMaker *make) {
                    make.left.equalTo(label);
                    make.top.equalTo(label.mas_bottom);
                    make.bottom.equalTo(cell.contentView);
                    make.right.equalTo(cell.contentView).offset(-15);
                }];
            }
        }
            break;
        case 2:
        {
            cell.textLabel.text = self.dataAray[indexPath.section][indexPath.row];
            cell.accessoryType = UITableViewCellAccessoryDisclosureIndicator;
            
        }
            break;
            
        default:
            cell.accessoryType = UITableViewCellAccessoryNone;

            break;
    }
    return cell;
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    if (indexPath.section == 1 && indexPath.row == 2) {
        return 75;
    }
    return 56;
}

-(UIView*)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section{
    if (section == 0) {
        
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
        namelabel.text = @"店铺名称";
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
    return nil;
}

-(CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section{
    if (section == 0) {
        
        return 85;
    }
    return 0;
}

-(CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section{
    return 1;
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{

    if (indexPath.section == 1 &&indexPath.row == 0) {
        CHInputAddressViewController *input = [CHInputAddressViewController new];
        [self.navigationController pushViewController:input animated:YES];
    }
}

@end
