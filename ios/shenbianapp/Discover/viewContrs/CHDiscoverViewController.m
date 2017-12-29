//
//  CHDiscoverViewController.m
//  shenbianapp
//
//  Created by book on 2017/10/29.
//  Copyright © 2017 helinkeji. All rights reserved.
//

#import "CHDiscoverViewController.h"

#import "CHDiscoverTableViewCell.h"
#import "CHServiceDetailsViewController.h"
#import "CHDiscoverModel.h"
@interface CHDiscoverViewController ()<UITableViewDelegate,UITableViewDataSource>

@property(nonatomic,strong) UITableView *tableView;
@property(nonatomic,strong) CHDiscoverModel *viewModel;
@property(nonatomic,copy)NSArray *dataArray;

@end

@implementation CHDiscoverViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    //Do any additional setup after loading the view.
    self.navBarView.mhBaseTitleLabel.text = @"发现";
    self.navigationController.navigationBar.tintColor = [UIColor colorWithHexColor:@"#008E8F"];

    [self.view addSubview:self.tableView];
    [self.tableView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.equalTo(self.view);
        make.top.equalTo(self.view).offset(64);
        make.bottom.equalTo(self.view).offset(-49);
    }];
    
}

-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    
    RACSignal *signal = [self.viewModel.loadPagedata execute:@{@"center":@"116.542951,39.639531",@"city":@"北京",@"pageNo":@"1"}];
    @weakify(self);
    [signal subscribeNext:^(id x) {
        @strongify(self);
        if (x) {
            self.dataArray = [x objectForKey:@"data"];
            [self.tableView reloadData];
        }
        NSLog(@"xxx:%@",x);
    } error:^(NSError *error) {
        NSLog(@"ddd:%@",error);
    }];
}

-(void)bindViewControllerModel{
    self.viewModel = [CHDiscoverModel new];
    //String center,String city,int pageNo

   

}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(UITableView *)tableView{

    if (_tableView == nil) {
        _tableView = [UITableView new];
        [_tableView registerClass:[CHDiscoverTableViewCell class] forCellReuseIdentifier:@"discoverCell"];
        _tableView.delegate = self;
        _tableView.dataSource = self;
        _tableView.tableFooterView = [UIView new];
    }
    return  _tableView;
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    CHDiscoverTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"discoverCell"];
    cell.modelDic = self.dataArray[indexPath.row];
    return cell;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{

    return self.dataArray.count;
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{

    return 300;
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    
    CHServiceDetailsViewController *serviceDetail = [CHServiceDetailsViewController new];
    NSString *serviceId = [self.dataArray[indexPath.row] objectForKey:@"id"];
    serviceDetail.serviceId =  serviceId;
    [self.navigationController pushViewController:serviceDetail animated:YES];
}

-(UIStatusBarStyle)preferredStatusBarStyle{

    return UIStatusBarStyleLightContent;
}

@end
