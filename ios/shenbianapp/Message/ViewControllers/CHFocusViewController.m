//
//  CHFocusViewController.m
//  shenbianapp
//
//  Created by book on 2017/11/11.
//  Copyright © 2017 helinkeji. All rights reserved.
//

#import "CHFocusViewController.h"
#import "CHFocusTableViewCell.h"
#import "CHFocusModel.h"
#import "CHPersonHomeViewController.h"

@interface CHFocusViewController ()<UITableViewDelegate,UITableViewDataSource>

@property(nonatomic,strong)UITableView *tableView;
@property(nonatomic,copy)NSArray *focusList;
@property(nonatomic,strong) CHFocusModel *viewModel;
@end

@implementation CHFocusViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = @"已关注";
}

-(CHFocusModel *)viewModel{
    if (!_viewModel) {
        _viewModel = [CHFocusModel new];
    }
    return _viewModel;
}

-(void)bindViewControllerModel{
    NSString *token = [[NSUserDefaults standardUserDefaults] objectForKey:@"token"];;
    if (token) {
        
    RACSignal *singal = [self.viewModel.loadPagedata execute:@{@"token":token}];
    @weakify(self);
    [singal subscribeNext:^(id x) {
        @strongify(self);
        self.focusList = [x objectForKey:@"data"];
        [self.tableView reloadData];
    } error:^(NSError *error) {
        NSLog(@"error:%@",error);
    }];
    } else {
        [[NSNotificationCenter defaultCenter] postNotificationName:kCHNotificationLogin object:nil];
    }
}

-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    self.tabBarController.tabBar.hidden = YES;
    self.navigationController.navigationBarHidden = NO;
}

-(void)viewWillDisappear:(BOOL)animated{
    [super viewWillDisappear:animated];
    self.tabBarController.tabBar.hidden = NO;
    self.navigationController.navigationBarHidden = YES;

}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
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
    cell.userDic = self.focusList[indexPath.row];
    return cell;
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{

    return 80;
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{

    CHPersonHomeViewController *personHome = [CHPersonHomeViewController new];
    personHome.userId = self.focusList[indexPath.row][@"userId"];
    personHome.userIcon = self.focusList[indexPath.row][@"userIcon"];
    personHome.userName = self.focusList[indexPath.row][@"userName"];
    [self.navigationController pushViewController:personHome animated:YES];
}

@end
