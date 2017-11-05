//
//  CHSeeMapViewController.m
//  shenbianapp
//
//  Created by book on 2017/11/5.
//  Copyright © 2017年 helinkeji. All rights reserved.
//

#import "CHSeeMapViewController.h"
#import <MAMapKit/MAMapKit.h>
#import <AMapFoundationKit/AMapFoundationKit.h>
@interface CHSeeMapViewController ()
@property(nonatomic,strong)MAMapView *mapView;
@end

@implementation CHSeeMapViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = @"服务位置";
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void)bindViewControllerModel{

}

-(void)setupViews{

    [self.view addSubview:self.mapView];

}

-(MAMapView *)mapView{
    if (_mapView == nil) {
        _mapView = [[MAMapView alloc]initWithFrame:(CGRectMake(0, 64, kScreenWidth, kScreenHeight - 113))];
        [AMapServices sharedServices].enableHTTPS = YES;
        _mapView.layer.cornerRadius = 10;
        _mapView.layer.borderWidth = 2;
        _mapView.layer.borderColor = [UIColor whiteColor].CGColor;
        [_mapView setZoomLevel:15];
        _mapView.showsUserLocation = YES;
        _mapView.userTrackingMode = MAUserTrackingModeFollow;
    }
    return _mapView;
}
@end
