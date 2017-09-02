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

@end

@implementation CHMapView

-(instancetype)initWithFrame:(CGRect)frame{
    if (self = [super initWithFrame:frame]) {
        self.backgroundColor = [UIColor redColor];
        [self addSubview:self.leftTopLabel];
        [self.leftTopLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(self);
            make.left.equalTo(self).offset(15);
            make.width.mas_equalTo(124);
            make.height.mas_equalTo(20);
        }];
        
        [self addSubview:self.rightTopLabel];
        [self.rightTopLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(self);
            make.right.equalTo(self).offset(-13);
            make.height.mas_equalTo(20);
            make.width.mas_equalTo(54);
        }];
        
        
        [AMapServices sharedServices].enableHTTPS = YES;
        MAMapView *apimapView = [[MAMapView alloc] initWithFrame:CGRectMake(0, 20, frame.size.width, frame.size.height)];
        [apimapView setZoomLevel:15 atPivot:self.center animated:YES];
        [self addSubview:apimapView];
        apimapView.showsUserLocation = YES;
        apimapView.userTrackingMode = MAUserTrackingModeFollow;
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
@end
