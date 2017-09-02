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
#import "CHKindView.h"

@interface RootHeaderViewController ()<HomeNavViewdelegate,CustomDiviceViewdelegate>

@property (nonatomic,strong)HomeNavView *NavView;
@property (nonatomic,strong)CustomDiviceView * headItemView;
@property (nonatomic,strong)NSMutableArray * headItemArray;
@property (nonatomic,copy)NSString * selectItemOneName;
@property (nonatomic,copy)NSString * selectItemTwoName;
@property (nonatomic,assign)NSInteger selectIndex;

@property (nonatomic,strong)UIView * infoCar;

@property (nonatomic,strong) RootObjectModel *viewCModel;

@property(nonatomic,strong) CHMapView *mapView;


@end

@implementation RootHeaderViewController
@dynamic viewCModel;


-(void)bindViewControllerModel{
    [super bindViewControllerModel];
    
    self.viewCModel = [[RootObjectModel alloc]init];
    
    NSDictionary *param = @{@"latitude":@"23.1230",@"longitude":@"36.023"};
   
    [self.viewCModel.loadPagedata execute:param];

    [RACObserve(self.viewCModel, loadModels) subscribeNext:^(NSDictionary *x) {
        self.NavView.quikSearchList = [x objectForKey:@"quik_search"];
        self.headItemView.categoryItemList = [x objectForKey:@"category_item"];

    }];
    
}

-(void)setupViews{

    [self.view addSubview:self.NavView];
    
    [self.view addSubview:self.headItemView];
    [self.headItemView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.NavView.mas_bottom);
        make.left.right.equalTo(self.view);
        make.height.mas_equalTo(220);
    }];
    
    [self.view addSubview:self.mapView];
    [self.mapView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.headItemView.mas_bottom);
        make.left.right.equalTo(self.view);
        make.height.mas_equalTo(102);
    }];
    
    
}

- (void)HomeNavViewClickIndex:(NSInteger)index{
    self.selectIndex = index;
    
}

- (void)CustomDiviceViewClickIndex:(NSInteger)index{
    

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
        _mapView = [[CHMapView alloc]init];
        _mapView.backgroundColor = [UIColor whiteColor];
    }
    return _mapView;
}

- (CustomDiviceView*)headItemView{
    if (!_headItemView) {
        _headItemView = [[CustomDiviceView alloc]init];
        _headItemView.delegate = self;
    }
    return _headItemView;
}



- (HomeNavView*)NavView{
    if (!_NavView) {
        _NavView = [[HomeNavView alloc]initWithFrame:CGRectMake(0, 24, kScreenWidth, 109)];
        _NavView.delegate = self;
    }
    return _NavView;
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
