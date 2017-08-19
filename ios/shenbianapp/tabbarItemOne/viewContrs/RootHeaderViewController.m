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
#import <MAMapKit/MAMapKit.h>
#import <AMapFoundationKit/AMapFoundationKit.h>
@interface RootHeaderViewController ()<HomeNavViewdelegate,CustomDiviceViewdelegate>
@property (nonatomic,strong)HomeNavView *NavView;
@property (nonatomic,strong)CustomDiviceView * headItemView;
@property (nonatomic,strong)NSMutableArray * headItemArray;
@property (nonatomic,copy)NSString * selectItemOneName;
@property (nonatomic,copy)NSString * selectItemTwoName;
@property (nonatomic,assign)NSInteger selectIndex;
@property (nonatomic,strong)UIView * mapView;
@property (nonatomic,strong)UIView * infoCar;
@end

@implementation RootHeaderViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.navBarView.mhBaseTitleLabel.hidden = YES;
    [self.navBarView addSubview:self.NavView];
//    self.headItemArray = [[NSMutableArray alloc]init];
//    self.selectIndex = 0;
//    self.selectItemOneName = @"玩具";
//    self.selectItemTwoName = @"送水";
//    NSArray * arrayOne = [[NSArray alloc]initWithObjects:@"玩具",@"充电宝",@"雨伞",@"服装",@"图书",nil];
//    NSArray * arraytwo = [[NSArray alloc]initWithObjects:@"送水",@"邮件",@"清洁",@"家教" ,@"洗衣",@"送花",nil];
//    NSMutableArray * mutableOne = [[NSMutableArray alloc]init];
//    for (int i =0; i<arrayOne.count; i++) {
//        RootObjectModel * model = [[RootObjectModel alloc]init];
//        model.object_name = arrayOne[i];
//        [mutableOne addObject:model];
//    }
//    
//    NSMutableArray * mutableTwo = [[NSMutableArray alloc]init];
//    for (int i =0; i<arraytwo.count; i++) {
//        RootObjectModel * model = [[RootObjectModel alloc]init];
//        model.object_name = arraytwo[i];
//        [mutableTwo addObject:model];
//    }
//    
//    [self.headItemArray addObject:mutableOne];
//    [self.headItemArray addObject:mutableTwo];
//    
//    [self.view addSubview:self.headItemView];
//    [self.headItemView setCustomDiviceViewByArray:self.headItemArray[0] andCureentObject:self.selectItemOneName];
    
    [AMapServices sharedServices].enableHTTPS = YES;
    [self.view addSubview:self.mapView];
     MAMapView *apimapView = [[MAMapView alloc] initWithFrame:self.mapView.bounds];
    [apimapView setZoomLevel:15 atPivot:self.mapView.center animated:YES];
    [self.mapView addSubview:apimapView];
    
    apimapView.showsUserLocation = YES;
    apimapView.userTrackingMode = MAUserTrackingModeFollow;
    
}

- (void)HomeNavViewClickIndex:(NSInteger)index{
    self.selectIndex = index;
    if (index==0) {
        [self.headItemView setCustomDiviceViewByArray:self.headItemArray[index] andCureentObject:self.selectItemOneName];
    }else{
       [self.headItemView setCustomDiviceViewByArray:self.headItemArray[index] andCureentObject:self.selectItemTwoName];
    }
}

- (void)CustomDiviceViewClickIndex:(NSInteger)index{
    RootObjectModel * model;
    if (self.selectIndex==0) {
        model = self.headItemArray[0][index];
        self.selectItemOneName =model.object_name;
    }else{
        model = self.headItemArray[1][index];
        self.selectItemTwoName = model.object_name;
    }
}

- (UIView*)infoCar{
    if (!_infoCar) {
        _infoCar = [[UIView alloc]initWithFrame:CGRectMake(0, 64+70, kScreenWidth, kScreenHeight-64-70-49)];
        _infoCar.backgroundColor = [UIColor whiteColor];
    }
    return _infoCar;
}

- (UIView*)mapView{
    if (!_mapView) {
        _mapView = [[UIView alloc]initWithFrame:CGRectMake(0, 64, kScreenWidth, kScreenHeight/2-64-49)];
        _mapView.backgroundColor = [UIColor lightGrayColor];
    }
    return _mapView;
}

- (CustomDiviceView*)headItemView{
    if (!_headItemView) {
        _headItemView = [[CustomDiviceView alloc]initWithFrame:CGRectMake(0, 64, kScreenWidth, 70)];
        _headItemView.delegate = self;
    }
    return _headItemView;
}

- (HomeNavView*)NavView{
    if (!_NavView) {
        _NavView = [[HomeNavView alloc]initWithFrame:CGRectMake((kScreenWidth-190)/2.0, 24, 190, 40)];
        _NavView.delegate = self;
    }
    return _NavView;
}



- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
