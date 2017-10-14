//
//  CHArticleContentView.m
//  shenbianapp
//
//  Created by book on 2017/9/24.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import "CHArticleContentView.h"

@interface CHArticleContentView ()

@property(nonatomic,strong)UIScrollView *articleScrollView;

@end

@implementation CHArticleContentView


-(instancetype)initWithFrame:(CGRect)frame{

    if (self = [super initWithFrame:frame]) {
        
        [self addSubview:self.articleScrollView];
        [self.articleScrollView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.edges.equalTo(self);
        }];
        
    }

    return self;
}

-(UIScrollView *)articleScrollView{
    
    if (!_articleScrollView) {
        _articleScrollView = [UIScrollView new];
        _articleScrollView.showsVerticalScrollIndicator = NO;
        _articleScrollView.showsHorizontalScrollIndicator = NO;
        _articleScrollView.backgroundColor = [UIColor orangeColor];
    }
    return _articleScrollView;
}
@end
