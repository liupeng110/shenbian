//
//  CHFJobSearchViewController.m
//  shenbianapp
//
//  Created by book on 2017/11/3.
//  Copyright © 2017年 helinkeji. All rights reserved.
//

#import "CHFJobSearchViewController.h"
#import "CHFindServiceHeadView.h"
#import "CHFindServicePopPanel.h"
#import "CHSearchJobTableViewCell.h"

@interface CHFJobSearchViewController ()<UITableViewDelegate,UITableViewDataSource>

@property(nonatomic,strong)CHFindServiceHeadView *headView;
@property(nonatomic,strong)UIButton *panelButton;
@property(nonatomic,strong)CHFindServicePopPanel *panelView;
@property(nonatomic,strong)UITableView *tableView;
@property(nonatomic,strong)NSArray *articleList;

@end

@implementation CHFJobSearchViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.

    self.navigationController.navigationBar.barTintColor = [UIColor colorWithHexString:@"#404040"];
    self.navigationController.navigationBar.barStyle = UIBarStyleBlackOpaque;

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
    self.headView.categoryList = @[@"创业",@"商务",@"科技",@"讲座",@"学术会议",@"竞赛",@"工程师",@"产品"];
    self.panelView.panelNameList = @[@"创业",@"商务",@"科技",@"讲座",@"学术会议",@"竞赛",@"工程师",@"产品"];
    
    self.articleList = @[@{},@{},@{}];
    
}
-(void)setupViews{
    
    [self.view addSubview:self.headView];
    
    [self.headView addSubview:self.panelButton];
    [self.panelButton mas_makeConstraints:^(MASConstraintMaker *make) {
        make.right.equalTo(self.headView);
        make.centerY.equalTo(self.headView);
        make.width.height.mas_equalTo(60);
    }];
    
    [self.view insertSubview:self.panelView belowSubview:self.headView];
    [self.panelView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.equalTo(self.view);
        make.bottom.equalTo(self.view.mas_top);
        make.height.mas_equalTo(150);
    }];
    
    [self.view insertSubview:self.tableView atIndex:0];
    [self.tableView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.headView.mas_bottom);
        make.left.right.bottom.equalTo(self);
    }];
    
}
-(CHFindServiceHeadView *)headView{
    
    if (_headView == nil) {
        _headView = [[CHFindServiceHeadView alloc]initWithFrame:(CGRectMake(0, 64, kScreenWidth, 48))];
        _headView.backgroundColor = [UIColor whiteColor];
    }
    return _headView;
}

-(UIButton *)panelButton{
    if (_panelButton == nil) {
        _panelButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_panelButton setImage:[UIImage imageNamed:@"zfw_sj"] forState:(UIControlStateNormal)];
        _panelButton.layer.shadowColor = [UIColor lightTextColor].CGColor;
        _panelButton.layer.shadowOpacity = 0.8;
        _panelButton.layer.cornerRadius = 20;
        _panelButton.backgroundColor = [UIColor lightTextColor];
        [_panelButton addTarget:self action:@selector(clickPanelButton:) forControlEvents:(UIControlEventTouchUpInside)];
    }
    return _panelButton;
}

- (void)clickPanelButton:(UIButton*)button{
    
    [self.panelView mas_updateConstraints:^(MASConstraintMaker *make) {
        if (button.tag == 0) {
            make.bottom.equalTo(self.view.mas_top).offset(260);
            button.tag = 1;
        } else {
            make.bottom.equalTo(self.view.mas_top);
            button.tag = 0;
        }
    }];
    [UIView animateWithDuration:0.25 animations:^{
        
        [self.view layoutIfNeeded];
    }];
    
}

-(CHFindServicePopPanel *)panelView{
    
    if (_panelView == nil) {
        _panelView = [[CHFindServicePopPanel alloc]init];
        _panelView.backgroundColor = [UIColor whiteColor];
    }
    return _panelView;
}

-(UITableView *)tableView{
    if (_tableView == nil) {
        _tableView = [UITableView new];
        _tableView.delegate = self;
        _tableView.dataSource = self;
        [_tableView registerClass:[CHSearchJobTableViewCell class] forCellReuseIdentifier:@"jobCell"];
        _tableView.tableFooterView = [UIView new];
        
    }
    return _tableView;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return self.articleList.count;
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    CHSearchJobTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"jobCell" forIndexPath:indexPath];
    cell.indexPath = indexPath;
    return cell;
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    return 120;
}

@end
