//
//  CHMapView.m
//  shenbianapp
//
//  Created by book on 2017/9/2.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import "CHMapView.h"

#import <MAMapKit/MAMapKit.h>
#import <AMapFoundationKit/AMapFoundationKit.h>

@interface CHMapView ()
@property(nonatomic,strong)UILabel *leftTopLabel;
@property(nonatomic,strong)UILabel *rightTopLabel;
@property(nonatomic,strong)MAMapView *apimapView;
@property(nonatomic,assign)CGRect originRect;
@end

@implementation CHMapView

-(instancetype)initWithFrame:(CGRect)frame{
    if (self = [super initWithFrame:frame]) {
        self.originRect =  frame;
        self.clipsToBounds = YES;


        [self addSubview:self.leftTopLabel];
        [self.leftTopLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(self);
            make.left.equalTo(self).offset(0);
            make.width.mas_equalTo(124);
            make.height.mas_equalTo(20);
        }];
        
        [self addSubview:self.rightTopLabel];
        [self.rightTopLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(self);
            make.right.equalTo(self).offset(0);
            make.height.mas_equalTo(20);
            make.width.mas_equalTo(54);
        }];
        [self addSubview:self.apimapView];
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


-(UILabel *)rightTopLabel{
    
    if (_rightTopLabel == nil) {
        _rightTopLabel = [[UILabel alloc]init];
        _rightTopLabel.text = @"See Map";
        _rightTopLabel.font = [UIFont fontWithName:@"PingFangSC-Regular" size:13];
        _rightTopLabel.textColor = [UIColor colorWithHexString:@"#009698"];
    }
    return _rightTopLabel;
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
    }
    return _apimapView;
}

-(void)setMapZoomSacle:(float)zoomScale animated:(BOOL)animated{
    
    [self.apimapView setZoomLevel:zoomScale atPivot:self.center animated:animated];
    
}
@end
