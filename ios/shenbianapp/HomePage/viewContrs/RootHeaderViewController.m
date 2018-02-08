//
//  RootHeaderViewController.m
//  shenbianapp
//
//  Created by   on 17/7/12.
//  Copyright © 2017  . All rights reserved.
//

#import "RootHeaderViewController.h"
#import "HomeNavView.h"
#import "RootObjectModel.h"
#import "CHCardsPanelView.h"
#import "CHMapView.h"
#import "CHOverbalanceView.h"

#import "CHMerchantView.h"

#import "CHServiceDetailsViewController.h"
#import "CHFindServiceViewController.h"
#import "CHFindPeopleViewController.h"
#import "CHServiceAllSerViewController.h"
#import "CHFJobSearchViewController.h"
#import "CHSeeMapViewController.h"
#import "CHShoppingCartViewController.h"
#import "CHLoacationSearchViewController.h"
#import "CHOverBalanceViewController.h"
#import "CHLoginViewController.h"
@interface RootHeaderViewController ()<UIScrollViewDelegate>

@property (nonatomic,strong)HomeNavView *NavView;
@property (nonatomic,strong)CHCardsPanelView * headItemView;
@property (nonatomic,strong) RootObjectModel *viewCModel;

@property(nonatomic,strong) CHMapView *mapView;


@property(nonatomic,strong)CHOverbalanceView *overBalanceView;

@property(nonatomic,strong) UIScrollView *wrapSrollview;

@property(nonatomic,strong) CHMerchantView *merchentView;


@end

@implementation RootHeaderViewController
@dynamic viewCModel;


-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    self.navigationController.navigationBarHidden = YES;

    @weakify(self);
    [RACObserve(GlobalData, currentLocation) subscribeNext:^(id x) {
        @strongify(self);
        if (x) {
            NSMutableDictionary *param = [NSMutableDictionary dictionaryWithDictionary: @{@"center":GlobalData.currentLocation,@"city":GlobalData.currentCity}];
            [self.viewCModel.loadTopData execute:param];
            
            NSDictionary *bottmParam = @{@"center":GlobalData.currentLocation,@"city":GlobalData.currentCity,@"page":@"1",@"limit":@"10",};
            [self.viewCModel.loadBottomData execute:bottmParam];
        }
    }];
}

-(void)bindViewControllerModel{
    
    @weakify(self);
    
    self.viewCModel = [[RootObjectModel alloc]init];
    
    
    
    [RACObserve(self.viewCModel, topDataList) subscribeNext:^(NSDictionary *x) {
        @strongify(self);
        if (x) {
            NSDictionary *tempDic = [x objectForKey:@"data"];
            self.NavView.quikSearchList = [tempDic objectForKey:@"categories"];
            self.headItemView.categoryItemList = [tempDic objectForKey:@"imgInfo"];
        }
    }];
    
    [RACObserve(self.viewCModel, bottomDataList) subscribeNext:^(NSDictionary *x) {
        @strongify(self);
        if (x) {
            NSDictionary *tempDic = [x objectForKey:@"data"];
            self.overBalanceView.overBablanceList = [tempDic objectForKey:@"greatValue"];;
            self.merchentView.merchentList = [tempDic objectForKey:@"vos"];
        }
    }];
    
    self.merchentView.selectedMerchant = ^(NSString *serviceID) {
        @strongify(self);
        CHServiceDetailsViewController *serviceDetailsVC = [[CHServiceDetailsViewController alloc]init];
        serviceDetailsVC.serviceId = serviceID;
        [self.navigationController pushViewController:serviceDetailsVC animated:YES];
    };
    
    self.headItemView.selectFindType = ^(NSString *firstCategoryId) {
        @strongify(self);
        CHFindPeopleViewController *vc = [CHFindPeopleViewController new];
        vc.firstCategoryId = firstCategoryId;
        [self.navigationController pushViewController:vc animated:YES];
    };
    
    self.mapView.seeAllLocation = ^{
        @strongify(self);
        CHSeeMapViewController *seeMap = [[CHSeeMapViewController alloc]init];
        [self.navigationController pushViewController:seeMap animated:YES];
    };
    
    
    self.NavView.goShoppingCart = ^{
        @strongify(self);
        NSString *token = [[NSUserDefaults standardUserDefaults] objectForKey:@"token"];
        if (token) {
            CHShoppingCartViewController *shoppingCart = [[CHShoppingCartViewController alloc]init];
            [self.navigationController pushViewController:shoppingCart animated:YES];
        } else {
            CHLoginViewController *login = [CHLoginViewController new];
            [self.tabBarController presentViewController:login animated:YES completion:nil];
            
        }
    };
    
    self.NavView.locationSearch = ^{
        @strongify(self);
        CHLoacationSearchViewController *locationSerch = [CHLoacationSearchViewController new];
        [self.navigationController pushViewController:locationSerch animated:YES];
    };
    
    self.overBalanceView.tapInOverBalence = ^(NSString *serviceId){
        @strongify(self);
        CHServiceDetailsViewController *serviceDetailsVC = [[CHServiceDetailsViewController alloc]init];
        serviceDetailsVC.serviceId = serviceId;
        [self.navigationController pushViewController:serviceDetailsVC animated:YES];
    };
//    self.overBalanceView.seeAllCategory = ^{
//        @strongify(self);
//        CHServiceAllSerViewController *allCategory = [CHServiceAllSerViewController new];
//        [self.navigationController pushViewController:allCategory animated:YES];
//    };
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
        make.height.mas_equalTo(120);
        make.width.mas_equalTo(kScreenWidth);
    }];
    
    [self.wrapSrollview addSubview:self.mapView];
    [self.mapView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.headItemView.mas_bottom).offset(0);
        make.left.right.equalTo(self.wrapSrollview);
        make.height.mas_equalTo(150);
    }];
    
    [self.wrapSrollview addSubview:self.overBalanceView];
    [self.overBalanceView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.mapView.mas_bottom).offset(15);
        make.left.equalTo(self.wrapSrollview).offset(15);
        make.right.equalTo(self.wrapSrollview).offset(-15);
        make.height.mas_equalTo(200);
    }];
    
    [self.wrapSrollview addSubview:self.merchentView];
    [self.merchentView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.overBalanceView.mas_bottom).offset(10);
        make.left.equalTo(self.wrapSrollview).offset(0);
        make.right.equalTo(self.wrapSrollview).offset(0);
        make.height.mas_equalTo(400);
        make.bottom.equalTo(self.wrapSrollview).offset(-49);
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

- (CHCardsPanelView*)headItemView{
    if (!_headItemView) {
        _headItemView = [[CHCardsPanelView alloc]init];
        
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
        _NavView = [[HomeNavView alloc]initWithFrame:CGRectMake(0, 20, kScreenWidth, 64)];
    }
    return _NavView;
}

- (UIScrollView *)wrapSrollview{
    
    if (_wrapSrollview == nil) {
        _wrapSrollview = [[UIScrollView alloc]init];
        _wrapSrollview.showsVerticalScrollIndicator = NO;
        _wrapSrollview.showsHorizontalScrollIndicator = NO;
        _wrapSrollview.backgroundColor = [UIColor whiteColor];
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
