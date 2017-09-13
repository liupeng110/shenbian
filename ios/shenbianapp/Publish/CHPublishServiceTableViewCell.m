//
//  CHPublishServiceTableViewCell.m
//  shenbianapp
//
//  Created by book on 2017/9/10.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import "CHPublishServiceTableViewCell.h"

@interface CHPublishServiceTableViewCell ()
@end

@implementation CHPublishServiceTableViewCell

-(instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier{

    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        
        [self addSubview:self.titleLabel];
        [self.titleLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self).offset(15);
            make.top.equalTo(self).offset(17);
            make.width.mas_equalTo(80);
            make.height.mas_equalTo(20);
        }];
        
        
    }
    return self;
}

-(UILabel *)titleLabel{

    if (_titleLabel == nil) {
        _titleLabel = [UILabel new];
        _titleLabel.font = [UIFont systemFontOfSize:15];
        _titleLabel.textColor = [UIColor colorWithHexString:@"#2d333a"];
        
    }
    return _titleLabel;
}

- (void)setServiceKind:(NSArray *)serviceKind{

    NSUInteger index = 0;
    CGFloat btnwidth = 90;
    CGFloat space = 18;
    for (NSString *name in serviceKind) {
        UIButton *button = [UIButton buttonWithType:(UIButtonTypeCustom)];
        button.tag = index;
        [button setTitle:name forState:(UIControlStateNormal)];
        [button setTitleColor:[UIColor colorWithHexString:@"#8f959c"] forState:(UIControlStateNormal)];

        button.titleLabel.font = [UIFont systemFontOfSize:15];
        [button addTarget:self action:@selector(clickServiceKindButton:) forControlEvents:(UIControlEventTouchUpInside)];
        button.backgroundColor = [UIColor colorWithHexString:@"#ebebeb"];
        if (index == 0) {
            button.backgroundColor = [UIColor colorWithHexString:@"#009698"];
            [button setTitleColor:[UIColor colorWithHexString:@"#fefefe"] forState:(UIControlStateNormal)];
        }
    
        button.frame = CGRectMake(15 + index *  (btnwidth + space) , 50, btnwidth, 30);
        button.layer.cornerRadius = 15;
        [self addSubview:button];
        
        index++;
    }

}


- (void)clickServiceKindButton:(UIButton*)button{
    
    for (UIView *view in self.subviews) {
        if ([view isKindOfClass:[UIButton class]]) {
            UIButton *btn = (UIButton*)view;
            btn.backgroundColor = [UIColor colorWithHexString:@"#ebebeb"];
            [btn setTitleColor:[UIColor colorWithHexString:@"#8f959c"] forState:(UIControlStateNormal)];

        }
    }
    
    button.backgroundColor = [UIColor colorWithHexString:@"#009698"];
    [button setTitleColor:[UIColor colorWithHexString:@"#fefefe"] forState:(UIControlStateNormal)];

}

@end
