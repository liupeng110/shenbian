//
//  CHMyOrdersViewController.m
//  shenbianapp
//
//  Created by book on 2017/11/10.
//  Copyright © 2017 helinkeji. All rights reserved.
//

#import "CHMyOrdersViewController.h"

#import "CHMyOrderTableViewCell.h"
#import "CHServiceDetailsViewController.h"
#import "CHMyOrderViewModel.h"
@interface CHMyOrdersViewController ()<UITableViewDelegate,UITableViewDataSource>
@property(nonatomic,strong)UITableView *tableView;
@property(nonatomic,copy)NSArray *orderList;

@end

@implementation CHMyOrdersViewController
@dynamic viewCModel;
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
    self.viewCModel = [CHMyOrderViewModel new];
    NSUserDefaults *ud = [NSUserDefaults standardUserDefaults];
    NSString *token = [ud  objectForKey:@"token"];
    NSString *userId = [ud objectForKey:@"userId"];
    RACSignal *signal = [self.viewCModel.loadPagedata execute:@{@"token":token,@"userId":userId}];
    [signal subscribeNext:^(id x) {
        self.orderList = [x objectForKey:@"data"];
        [self.tableView reloadData];
    } error:^(NSError *error) {
        
    }];
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
    cell.dataDic = self.orderList[self.orderList.count - 1 - indexPath.row];
    return cell;
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    return 110;
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{

    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    
//    CHServiceDetailsViewController *service = [CHServiceDetailsViewController new];
//    [self.navigationController pushViewController:service animated:YES];
    
}

-(void)clickBackButton{
    [self.navigationController popToRootViewControllerAnimated:YES];
}

@end
