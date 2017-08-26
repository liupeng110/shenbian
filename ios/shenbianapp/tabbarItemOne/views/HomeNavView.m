//
//  HomeNavView.m
//  Miaohi
//
//  Created by 杨绍智 on 17/5/8.
//  Copyright © 2017年 haiqiu. All rights reserved.
//

#import "HomeNavView.h"

@interface HomeNavView ()
@property(nonatomic,strong)UISearchBar *searchBar;
@property(nonatomic,strong)UIButton *locationButton;

@end

@implementation HomeNavView
- (instancetype)initWithFrame:(CGRect)frame{
    if (self = [super initWithFrame:frame]) {
        self.backgroundColor = [UIColor colorWithHexColor:@"#008e8f"];
        [self addSubview:self.locationButton];
        [self addSubview:self.searchBar];
    }
    return self;
}

-(UIButton *)locationButton{
    if (_locationButton == nil) {
        _locationButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        _locationButton.frame = CGRectMake(5, -5, 40, 40);
        [_locationButton setImage:[UIImage imageNamed:@"location"] forState:(UIControlStateNormal)];
    }
    return _locationButton;
}

-(UISearchBar *)searchBar{
    if (_searchBar == nil) {
        _searchBar = [[UISearchBar alloc]initWithFrame:(CGRectMake(60, -5, kScreenWidth - 120, 40))];
        _searchBar.backgroundColor = [UIColor clearColor];
        
    }
    return _searchBar;
}

@end
