//
//  CHFindServiceViewController.m
//  shenbianapp
//
//  Created by book on 2017/11/3.
//  Copyright © 2017年 helinkeji. All rights reserved.
//

#import "CHFindServiceViewController.h"

#import "CHFindServiceHeadView.h"
#import "CHFindServicePopPanel.h"
#import "CHFindServiceBrowseView.h"
#import "CHMapView.h"
#import "CHFindServiceOptimizedView.h"

@interface CHFindServiceViewController ()<UIScrollViewDelegate>
@property(nonatomic,strong)UIScrollView *wrapScrollView;
@property(nonatomic,strong)CHFindServiceHeadView *headView;
@property(nonatomic,strong)UIButton *panelButton;
@property(nonatomic,strong)CHFindServicePopPanel *panelView;
@property(nonatomic,strong)CHFindServiceBrowseView *browseView;
@property(nonatomic,strong) CHMapView *mapView;
@property(nonatomic,strong) CHFindServiceOptimizedView *optimizedView;

@property(nonatomic,strong)UIButton *joinButton;
@end

@implementation CHFindServiceViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = @"找服务";
    self.navigationController.navigationBar.barStyle = UIBarStyleBlackOpaque;
    self.navigationController.navigationBar.barTintColor = [UIColor colorWithHexString:@"#404040"];
    [self.rightButton setImage:[UIImage imageNamed:@"sy_gwc"] forState:(UIControlStateNormal)];
    self.rightButton.hidden = NO;
}

-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    self.tabBarController.tabBar.hidden = YES;
}

-(void)viewWillDisappear:(BOOL)animated{
    [super viewWillDisappear:animated];
    self.tabBarController.tabBar.hidden = NO;
}
-(void)clickRightButton{
    
    
}

-(void)bindViewControllerModel{
    self.headView.categoryList = @[@"美发美甲",@"寄快递",@"设计打印",@"维修",@"送鲜花",@"跑腿",@"",@""];
    self.panelView.panelNameList = @[@"专业美发",@"专业美甲",@"头发护理",@"修建指甲",@"指甲贴膜",@"",@"",@""];
    self.browseView.browseItemList = @[@"专业美发",@"专业美甲",@"头发护理",@"修建指甲",@"指甲贴膜",@"",@"",@""];
    self.optimizedView.optimizedItemList = @[@"专业美发",@"专业美甲",@"头发护理",@"修建指甲",@"指甲贴膜",@"",@"",@""];

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
        make.bottom.equalTo(self.headView.mas_top);
        make.height.mas_equalTo(165);
    }];
    
    
    [self.view insertSubview:self.wrapScrollView atIndex:0];
    [self.wrapScrollView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.headView.mas_bottom);
        make.left.equalTo(self.view);
        make.right.equalTo(self.view);
        make.bottom.equalTo(self.view).offset(-49);
    }];
    
    [self.wrapScrollView addSubview:self.browseView];
    [self.browseView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.wrapScrollView).offset(10);
        make.height.mas_equalTo(320);
        make.width.mas_equalTo(kScreenWidth);
    }];

    [self.wrapScrollView addSubview:self.mapView];
    [self.mapView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.browseView.mas_bottom);
        make.width.mas_equalTo(kScreenWidth);
        make.height.mas_equalTo(150);
    }];
    
    [self.wrapScrollView addSubview:self.optimizedView];
    [self.optimizedView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.mapView.mas_bottom).offset(20);
        make.width.mas_equalTo(kScreenWidth);
        make.height.mas_equalTo(500);
        make.bottom.equalTo(self.wrapScrollView);
    }];
    
    [self.view addSubview:self.joinButton];
    [self.joinButton mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.bottom.equalTo(self.view);
        make.height.mas_equalTo(55);
    }];
}
-(UIScrollView *)wrapScrollView{
    
    if (_wrapScrollView == nil) {
        _wrapScrollView = [[UIScrollView alloc]init];
        _wrapScrollView.contentSize = CGSizeMake(kScreenWidth, kScreenHeight);
        _wrapScrollView.showsVerticalScrollIndicator = NO;
        _wrapScrollView.delegate =  self;
    }
    return _wrapScrollView;
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
            make.bottom.equalTo(self.headView.mas_top).offset(200);
            button.tag = 1;
        } else {
            make.bottom.equalTo(self.headView.mas_top);
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

-(CHFindServiceBrowseView *)browseView{

    if (_browseView == nil) {
        _browseView = [[CHFindServiceBrowseView alloc]init];
    }
    return  _browseView;
}

- (UIView*)mapView{
    if (!_mapView) {
        _mapView = [[CHMapView alloc]init];
        _mapView.backgroundColor = [UIColor whiteColor];
        _mapView.layer.cornerRadius = 5;
    }
    return _mapView;
}

-(CHFindServiceOptimizedView *)optimizedView{
    if (_optimizedView == nil) {
        _optimizedView = [CHFindServiceOptimizedView new];
    }
    return _optimizedView;
}

-(UIButton *)joinButton{
    if (_joinButton == nil) {
        _joinButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        _joinButton.backgroundColor = [UIColor colorWithHexColor:@"#ff7f7a"];
        [_joinButton setTitle:@"加入我们" forState:(UIControlStateNormal)];
        [_joinButton addTarget:self action:@selector(clickJoinButton) forControlEvents:(UIControlEventTouchUpInside)];
    }
    return _joinButton;
}

-(void)clickJoinButton{

}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(UIStatusBarStyle)preferredStatusBarStyle{
    return UIStatusBarStyleLightContent;
}

-(void)scrollViewDidScroll:(UIScrollView *)scrollView{
    self.panelButton.tag = 1;
    [self clickPanelButton:self.panelButton];
}

@end
