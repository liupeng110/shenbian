//
//  CHFocusViewController.m
//  shenbianapp
//
//  Created by book on 2017/11/11.
//  Copyright © 2017年 helinkeji. All rights reserved.
//

#import "CHFocusViewController.h"
#import "CHFocusTableViewCell.h"

#import "CHArticleAndServiceListViewController.h"

@interface CHFocusViewController ()<UITableViewDelegate,UITableViewDataSource>

@property(nonatomic,strong)UITableView *tableView;
@property(nonatomic,copy)NSArray *focusList;
@end

@implementation CHFocusViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = @"已关注";
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
    self.focusList = @[@"",@"",@""];
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
        _tableView.dataSource = self;
        _tableView.delegate = self;
        _tableView.tableFooterView = [UIView new];
        _tableView.separatorColor = [UIColor colorWithHexColor:@"#ebebeb"];
        [_tableView registerClass:[CHFocusTableViewCell class] forCellReuseIdentifier:@"focusCell"];
    }
    return _tableView;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return self.focusList.count;
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    CHFocusTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"focusCell" forIndexPath:indexPath];
    return cell;
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{

    return 80;
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{

    CHArticleAndServiceListViewController *personHome = [CHArticleAndServiceListViewController new];
    [self.navigationController pushViewController:personHome animated:YES];
}

@end
