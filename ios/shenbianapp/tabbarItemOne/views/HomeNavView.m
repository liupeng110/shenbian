//
//  HomeNavView.m
//  Miaohi
//
//  Created by 杨绍智 on 17/5/8.
//  Copyright © 2017年 haiqiu. All rights reserved.
//

#import "HomeNavView.h"

@implementation HomeNavView
- (instancetype)initWithFrame:(CGRect)frame{
    if (self = [super initWithFrame:frame]) {
        self.backgroundColor = [UIColor blackColor];
        [self createUiPage];
    }
    return self;
}

- (void)createUiPage{
    NSArray * itemArray = [NSArray arrayWithObjects:@"物品共享",@"上门服务", nil];
    [self addSubview:self.lineLabel];
    for (int i =0; i<itemArray.count; i++) {
        UIButton *btn = [UIButton buttonWithType:UIButtonTypeRoundedRect];
        btn.tag =  100+i;
        [btn addTarget:self action:@selector(clickAction:) forControlEvents:UIControlEventTouchUpInside];
        btn.frame = CGRectMake((80+30)*i, 8,80, 18);
        [btn setTitle:itemArray[i] forState:UIControlStateNormal];
        [self addSubview:btn];
        if (i==0) {
            btn.titleLabel.font = [UIFont boldSystemFontOfSize:15.0];
            [btn setTitleColor:[UIColor colorWithHexColor:@"#ffffff"] forState:UIControlStateNormal];
            
            self.lineLabel.centerX = btn.centerX;
            self.lineLabel.frame = CGRectMake(btn.x-3, 36, btn.width+8, 4);
            
        }else{
            btn.titleLabel.font = [UIFont systemFontOfSize:14.0];
            [btn setTitleColor:[UIColor colorWithHexColor:@"#666666"] forState:UIControlStateNormal];
        }

    }
}

- (void)setBtnValue:(NSInteger)index{
    UIButton *btn = (UIButton*)[self viewWithTag:100+index];
    btn.titleLabel.font = [UIFont boldSystemFontOfSize:15.0];
    [btn setTitleColor:[UIColor colorWithHexColor:@"#ffffff"] forState:UIControlStateNormal];
    self.lineLabel.centerX = btn.centerX;
    self.lineLabel.frame = CGRectMake(btn.x-3, 36, btn.width+8, 4);
    if (index==0) {
       UIButton *btn = (UIButton*)[self viewWithTag:101];
        btn.titleLabel.font = [UIFont systemFontOfSize:14.0];
        [btn setTitleColor:[UIColor colorWithHexColor:@"#666666"] forState:UIControlStateNormal];
    }else{
       UIButton *btn = (UIButton*)[self viewWithTag:100];
        btn.titleLabel.font = [UIFont systemFontOfSize:14.0];
        [btn setTitleColor:[UIColor colorWithHexColor:@"#666666"] forState:UIControlStateNormal];
    }
}

- (void)clickAction:(UIButton*)sender{
    NSArray * viewArray =self.subviews;
    for (UIView * view in viewArray) {
        if ([view isKindOfClass:[UIButton class]]) {
            UIButton * tem = (UIButton*)view;
            if (tem.tag==sender.tag) {
                sender.titleLabel.font = [UIFont boldSystemFontOfSize:15.0];
                [sender setTitleColor:[UIColor colorWithHexColor:@"#ffffff"] forState:UIControlStateNormal];
                self.lineLabel.centerX = sender.centerX;
                self.lineLabel.frame = CGRectMake(sender.x-3, 36, sender.width+8, 4);
                if (self.delegate && [self.delegate respondsToSelector:@selector(HomeNavViewClickIndex:)]) {
                    [self.delegate HomeNavViewClickIndex:sender.tag-100];
                }
            }else{
                tem.titleLabel.font = [UIFont systemFontOfSize:14.0];
                [tem setTitleColor:[UIColor colorWithHexColor:@"#666666"] forState:UIControlStateNormal];
            }
        }
    }
    
}
- (UILabel*)lineLabel{
    if (!_lineLabel) {
        _lineLabel = [[UILabel alloc] initWithFrame:CGRectMake(0, 36, 35, 4)];
        _lineLabel.backgroundColor = [UIColor colorWithHexColor:@"#ffffff"];
    }
    return _lineLabel;
}
@end
