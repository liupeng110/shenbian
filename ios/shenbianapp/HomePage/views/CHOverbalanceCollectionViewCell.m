//
//  CHKindCollectionViewCell.m
//  shenbianapp
//
//  Created by book on 2017/9/2.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import "CHOverbalanceCollectionViewCell.h"

@interface CHOverbalanceCollectionViewCell ()
@property(nonatomic,strong)UIImageView *coverImageView;
@end

@implementation CHOverbalanceCollectionViewCell

-(instancetype)initWithFrame:(CGRect)frame{

    if (self = [super initWithFrame:frame]) {
        self.contentView.clipsToBounds = YES;
        self.contentView.layer.cornerRadius = 5;
//        self.backgroundColor = [UIColor redColor];
//        self.contentView.layer.borderWidth = 0.7f;
        self.contentView.layer.masksToBounds = YES;
        [self.contentView addSubview:self.coverImageView];
        [self.coverImageView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.edges.equalTo(self.contentView);
        }];
    }
    return self;
}


-(UIImageView *)coverImageView{

    if (_coverImageView == nil) {
        
        _coverImageView = [UIImageView new];
        _coverImageView.contentMode = UIViewContentModeScaleAspectFit;
        _coverImageView.layer.borderColor = [UIColor whiteColor].CGColor;
        _coverImageView.clipsToBounds = YES;
        _coverImageView.layer.cornerRadius = 5;
    }
    return _coverImageView;
}

- (void)setModel:(CHOverbalanceModel *)model{
    
    [self.coverImageView setImageWithURL:[NSURL URLWithString:model.coverUrl] placeholder:[UIImage imageNamed:model.placeHolder]];
}

@end
