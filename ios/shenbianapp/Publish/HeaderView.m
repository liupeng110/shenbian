//
//  HeaderView.m
//  Extand TableView
//
//  Created by shenliping on 16/4/14.
//  Copyright © 2016年 shenliping. All rights reserved.
//

#import "HeaderView.h"

@implementation HeaderView

- (instancetype)initWithFrame:(CGRect)frame IsOpen:(BOOL)isOpen {
    if (self = [super initWithFrame:frame]) {
        self.backgroundColor = [UIColor whiteColor];
        [self addSubview:self.nameLabel];
        [self addSubview:self.imageView];
        [self addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(tapOpen)]];
        self.isOpen = isOpen;
        if (self.isOpen) {
            _imageView.transform = CGAffineTransformRotate(_imageView.transform, M_PI / 2);
        }
        UIView *line = [UIView new];
        line.backgroundColor = [UIColor colorWithHexColor:@"#ebebeb"];
        [self addSubview:line];
        [line mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self).offset(15);
            make.right.equalTo(self);
            make.height.mas_equalTo(1);
            make.width.equalTo(self);
            make.bottom.equalTo(self);
        }];
        
       
    }
    return self;
}

-(void)setSection:(NSInteger)section{
    _section = section;
    if (_section == 0) {
        self.imageView.image = [UIImage imageNamed:@"xiajiantou"];

    } else if (section == 1){
        
        UITextField *textFiled = [UITextField new];
        textFiled.placeholder = @"请输入价格";
        textFiled.font = [UIFont systemFontOfSize:13];
        textFiled.textColor = [UIColor colorWithHexColor:@"#8f959c"];
        textFiled.textAlignment = NSTextAlignmentRight;
        [self addSubview:textFiled];
        [textFiled mas_makeConstraints:^(MASConstraintMaker *make) {
            make.right.equalTo(self).offset(-30);
            make.width.mas_equalTo(120);
            make.height.mas_equalTo(30);
            make.centerY.equalTo(self);
        }];
    } else if (section == 2 || section == 4){
        self.imageView.image = [UIImage imageNamed:@"publish_detail"];

    }
    if (_section == 3) {
        [self addButtons];
    }
}

- (void)addButtons{
    
    NSUInteger index = 0;
    CGFloat btnwidth = 90;
    CGFloat space = 18;
    NSArray *serviceKind = @[@"在线服务",@"到店服务",@"上门服务"];
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

- (UIImageView *)imageView{
    if (_imageView == nil) {
        _imageView = [[UIImageView alloc] initWithFrame:CGRectMake(kScreenWidth - 35, 10, 20, 20)];
        _imageView.image = [UIImage imageNamed:@"右边.png"];
    }
    return _imageView;
}

- (UILabel *)nameLabel{
    if (_nameLabel == nil) {
        _nameLabel = [[UILabel alloc] initWithFrame:CGRectMake(15, 10, 100, self.frame.size.height - 20)];
        _nameLabel.font = [UIFont systemFontOfSize:15];
    }
    return _nameLabel;
}


- (void)tapOpen{
    if (_isOpen) {
//        [UIView animateWithDuration:0.3 animations:^{
//            _imageView.transform = CGAffineTransformRotate(_imageView.transform, -M_PI / 2);
//        }];
        if (self.closeblock) {
            self.closeblock(self.section);
        }
    }else{
//        [UIView animateWithDuration:0.3 animations:^{
//            _imageView.transform = CGAffineTransformRotate(_imageView.transform, M_PI / 2);
//        }];
        if (self.openblock) {
            self.openblock(self.section);
        }
    }
    self.isOpen = !self.isOpen;
}

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
