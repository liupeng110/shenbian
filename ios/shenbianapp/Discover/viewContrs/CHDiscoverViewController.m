//
//  CHDiscoverViewController.m
//  shenbianapp
//
//  Created by book on 2017/10/29.
//  Copyright © 2017年 helinkeji. All rights reserved.
//

#import "CHDiscoverViewController.h"

#import "CHDiscoverTableViewCell.h"

@interface CHDiscoverViewController ()<UITableViewDelegate,UITableViewDataSource>

@property(nonatomic,strong) UITableView *tableView;

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
    }
    return  _tableView;
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    CHDiscoverTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"discoverCell"];
    
    return cell;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{

    return 2;

}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{


    return 300;
}

-(UIStatusBarStyle)preferredStatusBarStyle{

    return UIStatusBarStyleLightContent;
}

@end
