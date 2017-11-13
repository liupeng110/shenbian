//
//  CHMyOrdersViewController.m
//  shenbianapp
//
//  Created by book on 2017/11/10.
//  Copyright © 2017年 helinkeji. All rights reserved.
//

#import "CHMyOrdersViewController.h"

#import "CHMyOrderTableViewCell.h"
#import "CHServiceDetailsViewController.h"
@interface CHMyOrdersViewController ()<UITableViewDelegate,UITableViewDataSource>
@property(nonatomic,strong)UITableView *tableView;
@property(nonatomic,copy)NSArray *orderList;
@end

@implementation CHMyOrdersViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = @"订单";
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void)bindViewControllerModel{

    self.orderList = @[@"",@"",@""];
    
}

-(void)setupViews{

    [self.view addSubview:self.tableView];
    [self.tableView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.bottom.equalTo(self.view);
        make.top.equalTo(self.view).offset(64);
    }];
}

-(UITableView *)tableView{

    if (_tableView == nil) {
        _tableView = [UITableView new];
        _tableView.delegate = self;
        _tableView.dataSource = self;
        [_tableView registerClass:[CHMyOrderTableViewCell class] forCellReuseIdentifier:@"orderCell"];
        _tableView.tableFooterView = [UIView new];
        _tableView.separatorColor = [UIColor colorWithHexColor:@"#ebebeb"];
    }
    return _tableView;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return self.orderList.count;
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    CHMyOrderTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"orderCell" forIndexPath:indexPath];
    return cell;
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    return 110;
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{

    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    
    CHServiceDetailsViewController *service = [CHServiceDetailsViewController new];
    [self.navigationController pushViewController:service animated:YES];
    
}

@end
