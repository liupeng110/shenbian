//
//  CHMapView.m
//  shenbianapp
//
//  Created by book on 2017/9/2.
//  Copyright © 2017 . All rights reserved.
//

#import "CHMapView.h"

#import <MAMapKit/MAMapKit.h>
#import <AMapFoundationKit/AMapFoundationKit.h>

@interface CHMapView ()
@property(nonatomic,strong)UILabel *leftTopLabel;
@property(nonatomic,strong)UIButton *seeAllButton;
@property(nonatomic,strong)MAMapView *apimapView;
@property(nonatomic,assign)CGRect originRect;
@end

@implementation CHMapView

-(instancetype)initWithFrame:(CGRect)frame{
    if (self = [super initWithFrame:frame]) {
        self.originRect =  frame;
        self.clipsToBounds = YES;
        self.layer.masksToBounds = YES;

        [self addSubview:self.leftTopLabel];
        [self.leftTopLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(self);
            make.left.equalTo(self).offset(15);
            make.width.mas_equalTo(124);
            make.height.mas_equalTo(20);
        }];
        
        [self addSubview:self.seeAllButton];
        [self.seeAllButton mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(self);
            make.right.equalTo(self).offset(-15);
            make.height.mas_equalTo(20);
            make.width.mas_equalTo(60);
        }];
        [self addSubview:self.apimapView];
        [self.apimapView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.right.equalTo(self).offset(-15);
            make.left.equalTo(self).offset(15);
            make.top.equalTo(self).offset(20);
            make.bottom.equalTo(self);
        }];
    }
    return self;
}

-(UILabel *)leftTopLabel{
    
    if (_leftTopLabel == nil) {
        _leftTopLabel = [[UILabel alloc]init];
        _leftTopLabel.text = @"NEARBY EVENTS";
        _leftTopLabel.font = [UIFont fontWithName:@"PingFangSC-Regular" size:15];
    }
    return _leftTopLabel;
}


-(UIButton *)seeAllButton{
    if (_seeAllButton == nil) {
        _seeAllButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_seeAllButton setTitle:@"See Map" forState:(UIControlStateNormal)];
        [_seeAllButton.titleLabel setFont:[UIFont systemFontOfSize:15]];
        [_seeAllButton setTitleColor:[UIColor colorWithHexColor:@"#009698"] forState:(UIControlStateNormal)];
        [_seeAllButton addTarget:self action:@selector(clickSeeAllButton) forControlEvents:(UIControlEventTouchUpInside)];
    }
    return _seeAllButton;
}

-(void)clickSeeAllButton{
    if (self.seeAllLocation) {
        self.seeAllLocation();
    };
}
-(MAMapView *)apimapView{
    if (_apimapView == nil) {
        _apimapView = [[MAMapView alloc]initWithFrame:(CGRectMake(0, 20, 1, 1))];
        [AMapServices sharedServices].enableHTTPS = YES;
        _apimapView.layer.cornerRadius = 10;
        _apimapView.layer.borderWidth = 2;
        _apimapView.layer.borderColor = [UIColor whiteColor].CGColor;
        [_apimapView setZoomLevel:15];
        _apimapView.showsUserLocation = YES;
        _apimapView.userTrackingMode = MAUserTrackingModeFollow;
        _apimapView.scrollEnabled = NO;
    }
    return _apimapView;
}

-(void)setMapZoomSacle:(float)zoomScale animated:(BOOL)animated{
    
    [self.apimapView setZoomLevel:zoomScale atPivot:self.center animated:animated];
    
}
@end
