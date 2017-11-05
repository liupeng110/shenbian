//
//  CHServiceUperView.m
//  shenbianapp
//
//  Created by book on 2017/9/14.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import "CHServiceUperView.h"

@interface CHServiceUperView ()
@property(nonatomic,strong)UIButton *collectButton;
@property(nonatomic,strong)UIButton *topShareButton;
@property(nonatomic,strong)UIImageView *introduceImageView;
@end

@implementation CHServiceUperView

-(instancetype)initWithFrame:(CGRect)frame{

    if (self = [super initWithFrame:frame]) {
        self.backgroundColor = [UIColor redColor];
        
        [self addSubview:self.introduceImageView];
        [self.introduceImageView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.edges.equalTo(self);
        }];
        
        [self addSubview:self.topShareButton];
        [self.topShareButton mas_makeConstraints:^(MASConstraintMaker *make) {
            make.right.equalTo(self).offset(-15);
            make.top.equalTo(self).offset(20);
            make.width.mas_equalTo(40);
            make.height.mas_equalTo(40);
        }];
        
        
        [self addSubview:self.collectButton];
        [self.collectButton mas_makeConstraints:^(MASConstraintMaker *make) {
            make.right.equalTo(self.topShareButton.mas_left).offset(-15);
            make.top.equalTo(self).offset(20);
            make.width.mas_equalTo(40);
            make.height.mas_equalTo(40);
        }];
       
    }
    return self;
}

-(UIButton *)topShareButton{
    if (_topShareButton == nil) {
        _topShareButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_topShareButton setImage:[UIImage imageNamed:@"ydwz_zf"] forState:(UIControlStateNormal)];
        [_topShareButton addTarget:self action:@selector(clickShareButton) forControlEvents:(UIControlEventTouchUpInside)];
    }
    
    return _topShareButton;
}

-(void)clickShareButton{
    
}

-(UIButton *)collectButton{
    if (_collectButton == nil) {
        _collectButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        _collectButton.frame = CGRectMake(0, 0, 40, 40);
        [_collectButton setImage:[UIImage imageNamed:@"ydwz_sc"] forState:UIControlStateNormal];
    }
    return _collectButton;
}

-(UIImageView *)introduceImageView{
    
    if (_introduceImageView == nil) {
        _introduceImageView = [[UIImageView alloc]initWithImage:[UIImage imageNamed:@"default_image"]];
    }
    return _introduceImageView;
    
}



@end
