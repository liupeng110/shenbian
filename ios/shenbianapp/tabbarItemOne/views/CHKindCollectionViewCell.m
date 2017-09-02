//
//  CHKindCollectionViewCell.m
//  shenbianapp
//
//  Created by book on 2017/9/2.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import "CHKindCollectionViewCell.h"

@interface CHKindCollectionViewCell ()
@property(nonatomic,strong)UIImageView *coverImageView;
@end

@implementation CHKindCollectionViewCell

-(instancetype)initWithFrame:(CGRect)frame{

    if (self = [super initWithFrame:frame]) {
        [self addSubview:self.coverImageView];
        [self.coverImageView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.edges.equalTo(self);
        }];
    }
    return self;
}


-(UIImageView *)coverImageView{

    if (_coverImageView == nil) {
        
        _coverImageView = [UIImageView new];
        _coverImageView.contentMode = UIViewContentModeScaleAspectFill;
        
    }
    return _coverImageView;
}

- (void)setModel:(CHKindModel *)model{
    
    
}

@end
