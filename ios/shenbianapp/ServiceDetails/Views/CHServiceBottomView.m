//
//  CHServiceBottomView.m
//  shenbianapp
//
//  Created by book on 2017/9/14.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import "CHServiceBottomView.h"
@interface CHServiceBottomView()

@property(nonatomic,strong)UIButton *sendMessageButton;
@property(nonatomic,strong)UIButton *makeOrderButton;

@end


@implementation CHServiceBottomView

-(instancetype)initWithFrame:(CGRect)frame{

    if ([super initWithFrame:frame]) {
        
        [self addSubview:self.sendMessageButton];
        [self.sendMessageButton mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self);
            make.bottom.equalTo(self);
            make.width.mas_equalTo(kScreenWidth/2.0);
            make.height.mas_equalTo(55);
        }];
        [self addSubview:self.makeOrderButton];
        [self.makeOrderButton mas_makeConstraints:^(MASConstraintMaker *make) {
            make.right.bottom.equalTo(self);
            make.width.mas_equalTo(kScreenWidth/2.0);
            make.height.mas_equalTo(55);
        }];
    }
    return self;
}


- (UIButton *)sendMessageButton{

    if (_sendMessageButton == nil) {
        _sendMessageButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_sendMessageButton setTitle:@"发消息" forState:(UIControlStateNormal)];
        [_sendMessageButton setBackgroundColor:[UIColor colorWithHexString:@"#ff7f7a"]];
    }
    
    return _sendMessageButton;

}

-(UIButton *)makeOrderButton{

    if (_makeOrderButton == nil) {
        _makeOrderButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_makeOrderButton setTitle:@"下单" forState:(UIControlStateNormal)];
        [_makeOrderButton setTitleColor:[UIColor colorWithHexString:@""] forState:(UIControlStateNormal)];
        [_makeOrderButton setBackgroundColor:[UIColor colorWithHexString:@"#01b0f0"]];
    }
    
    return _makeOrderButton;
}

@end
