//
//  CHShoppingCartViewController.m
//  shenbianapp
//
//  Created by book on 2017/11/6.
//  Copyright © 2017年 helinkeji. All rights reserved.
//

#import "CHShoppingCartViewController.h"
#import "CHShoppingCartTableViewCell.h"
#import "CHSubmitOrderViewController.h"
#import "CHServiceDetailsViewController.h"
@interface CHShoppingCartViewController ()<UITableViewDelegate,UITableViewDataSource>
@property(nonatomic,strong)UITableView *tableView;
@property(nonatomic,strong)UIButton *makeOrderButton;
@end

@implementation CHShoppingCartViewController

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
    
    
}

-(void)setupViews{
   
    
    [self.view addSubview:self.tableView];
    [self.tableView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.bottom.equalTo(self.view);
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
    }
    return  _tableView;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return 3;
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    CHShoppingCartTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"cartCell" forIndexPath:indexPath];
    
    return cell;
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{

    return 106;
}

-(CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section{
    return 85;
}
-(UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section{
    
    UIView *contentView = [[UIView alloc]initWithFrame:(CGRectMake(0, 0, kScreenWidth, 85))];
    contentView.layer.borderWidth = 0.5;
    contentView.layer.borderColor = [UIColor lightGrayColor].CGColor;
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

-(BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath{

    return YES;
}

-(void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath{

}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    CHServiceDetailsViewController *serviceDetail = [CHServiceDetailsViewController new];
    [self.navigationController pushViewController:serviceDetail animated:YES];
    
}

-(void)clickRightButton{

}

-(UIButton *)makeOrderButton{

    if (_makeOrderButton == nil) {
        _makeOrderButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_makeOrderButton setTitle:@"合计 11 下单" forState:(UIControlStateNormal)];
        _makeOrderButton.backgroundColor = [UIColor colorWithHexColor:@"#009698"];
        [_makeOrderButton addTarget:self action:@selector(clickMakeOrderButton) forControlEvents:(UIControlEventTouchUpInside)];
    }
    
    return _makeOrderButton;
}

- (void)clickMakeOrderButton{
    CHSubmitOrderViewController *submitOrder = [CHSubmitOrderViewController new];
    [self.navigationController pushViewController:submitOrder animated:YES];
}

@end
