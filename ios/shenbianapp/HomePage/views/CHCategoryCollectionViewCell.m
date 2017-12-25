//
//  CHCategoryCollectionViewCell.m
//  shenbianapp
//
//  Created by book on 2017/9/2.
//  Copyright © 2017 . All rights reserved.
//

#import "CHCategoryCollectionViewCell.h"

@interface CHCategoryCollectionViewCell ()
@property(nonatomic,strong) UIImageView *iconImageView;
@property(nonatomic,strong) UILabel *nameLabel;

@end

@implementation CHCategoryCollectionViewCell

-(instancetype)initWithFrame:(CGRect)frame{

    if (self = [super initWithFrame:frame]) {

        
        [self addSubview:self.iconImageView];
        [self.iconImageView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.centerX.equalTo(self);
            make.top.equalTo(self).offset(20);
            make.width.height.mas_equalTo(50);
            
        }];
        
        [self addSubview:self.nameLabel];
        [self.nameLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.right.equalTo(self);
            make.bottom.equalTo(self);
            make.height.mas_equalTo(20);
        }];
    }

    return self;
}

-(UIImageView *)iconImageView{
    if (_iconImageView == nil) {
        _iconImageView = [[UIImageView alloc]init];
        _iconImageView.contentMode = UIViewContentModeScaleAspectFill;
        _iconImageView.layer.cornerRadius = 5;
    }
    return _iconImageView;
}

-(UILabel *)nameLabel{

    if (_nameLabel == nil) {
        _nameLabel = [UILabel new];
        _nameLabel.textAlignment = NSTextAlignmentCenter;
        _nameLabel.font = [UIFont systemFontOfSize:12];
    }
    return _nameLabel;
}


-(void)setItemModel:(CHCategoryItemModel *)itemModel{
    [self.iconImageView setImageWithURL:[NSURL URLWithString:itemModel.iconUrl] placeholder:[UIImage imageNamed:itemModel.placeHolder]];
    self.nameLabel.text = itemModel.name;

}

@end
