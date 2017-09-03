//
//  RootHeaderViewController.m
//  shenbianapp
//
//  Created by 杨绍智 on 17/7/12.
//  Copyright © 2017年 杨绍智. All rights reserved.
//

#import "RootHeaderViewController.h"
#import "HomeNavView.h"
#import "RootObjectModel.h"
#import "CustomDiviceView.h"
#import "CHMapView.h"
#import "CHOverbalanceView.h"

#import "CHMerchantView.h"


@interface RootHeaderViewController ()<UIScrollViewDelegate>

@property (nonatomic,strong)HomeNavView *NavView;
@property (nonatomic,strong)CustomDiviceView * headItemView;
@property (nonatomic,strong) RootObjectModel *viewCModel;

@property(nonatomic,strong) CHMapView *mapView;


@property(nonatomic,strong)CHOverbalanceView *overBalanceView;

@property(nonatomic,strong) UIScrollView *wrapSrollview;

@property(nonatomic,strong) CHMerchantView *merchentView;


@end

@implementation RootHeaderViewController
@dynamic viewCModel;

-(void)viewDidAppear:(BOOL)animated{
    [super viewDidAppear:animated];
//    [self.mapView setMapZoomSacle:15 animated:NO];
}

-(void)bindViewControllerModel{
    [super bindViewControllerModel];
    
    self.viewCModel = [[RootObjectModel alloc]init];
    
    NSDictionary *param = @{@"latitude":@"23.1230",@"longitude":@"36.023"};
    
    [self.viewCModel.loadPagedata execute:param];
    
    [RACObserve(self.viewCModel, loadModels) subscribeNext:^(NSDictionary *x) {
        if (x) {
            self.NavView.quikSearchList = [x objectForKey:@"quik_search"];
            self.headItemView.categoryItemList = [x objectForKey:@"category_item"];
            self.overBalanceView.overBablanceList = [x objectForKey:@"over_balance"];
            self.merchentView.merchentList = [x objectForKey:@"merchent"];
        }
    }];
}

-(void)setupViews{
    
    [self.view addSubview:self.NavView];

    [self.view addSubview:self.wrapSrollview];
    [self.wrapSrollview mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.NavView.mas_bottom);
        make.left.equalTo(self.view);
        make.right.equalTo(self.view);
        make.bottom.equalTo(self.view);
    }];
    
    [self.wrapSrollview addSubview:self.headItemView];
    [self.headItemView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.wrapSrollview);
        make.left.right.equalTo(self.wrapSrollview);
        make.height.mas_equalTo(200);
        make.width.mas_equalTo(kScreenWidth);
    }];
    
    [self.wrapSrollview addSubview:self.mapView];
    [self.mapView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.headItemView.mas_bottom);
        make.left.equalTo(self.wrapSrollview).offset(15);
        make.right.equalTo(self.wrapSrollview).offset(-15);
        make.height.mas_equalTo(120);
    }];
    
    [self.wrapSrollview addSubview:self.overBalanceView];
    [self.overBalanceView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.mapView.mas_bottom).offset(10);
        make.left.equalTo(self.wrapSrollview).offset(15);
        make.right.equalTo(self.wrapSrollview).offset(-15);
        make.height.mas_equalTo(150);
    }];
    

    [self.wrapSrollview addSubview:self.merchentView];
    [self.merchentView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.overBalanceView.mas_bottom).offset(10);
        make.left.equalTo(self.wrapSrollview).offset(0);
        make.right.equalTo(self.wrapSrollview).offset(0);
        make.height.mas_equalTo(300);
        make.bottom.equalTo(self.wrapSrollview);
    }];
}


- (UIView*)mapView{
    if (!_mapView) {
        _mapView = [[CHMapView alloc]init];
        _mapView.backgroundColor = [UIColor whiteColor];
        _mapView.layer.cornerRadius = 5;
    }
    return _mapView;
}

- (CustomDiviceView*)headItemView{
    if (!_headItemView) {
        _headItemView = [[CustomDiviceView alloc]init];

    }
    return _headItemView;
}

-(CHOverbalanceView *)overBalanceView{

    if (_overBalanceView == nil) {
        _overBalanceView = [[CHOverbalanceView alloc] init];
        _overBalanceView.layer.cornerRadius = 5;
    }

    return _overBalanceView;
}

- (HomeNavView*)NavView{
    if (!_NavView) {
        _NavView = [[HomeNavView alloc]initWithFrame:CGRectMake(0, 20, kScreenWidth, 109)];
    }
    return _NavView;
}



- (UIScrollView *)wrapSrollview{

    if (_wrapSrollview == nil) {
        _wrapSrollview = [[UIScrollView alloc]init];
        _wrapSrollview.showsVerticalScrollIndicator = NO;
        _wrapSrollview.showsHorizontalScrollIndicator = NO;
//        _wrapSrollview.backgroundColor = [UIColor purpleColor];
        _wrapSrollview.contentSize = CGSizeMake(kScreenWidth, kScreenHeight);
    }
    return _wrapSrollview;
}

-(CHMerchantView *)merchentView{

    if (_merchentView == nil) {
        _merchentView = [[CHMerchantView alloc]init];
        _merchentView.backgroundColor = [UIColor redColor];
    }
    return _merchentView;
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}



@end
