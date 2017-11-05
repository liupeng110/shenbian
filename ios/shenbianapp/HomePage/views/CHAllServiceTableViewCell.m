//
//  CHAllServiceTableViewCell.m
//  shenbianapp
//
//  Created by book on 2017/11/5.
//  Copyright © 2017 helinkeji. All rights reserved.
//

#import "CHAllServiceTableViewCell.h"

@interface CHAllServiceTableViewCell ()
@property(nonatomic,strong)UIImageView *iconImage;
@property(nonatomic,strong)UILabel *categoryLabel;
@property(nonatomic,strong)UILabel *descriptionLabel;

@end

@implementation CHAllServiceTableViewCell

-(instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{

    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        [self addSubview:self.iconImage];
        [self.iconImage mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.top.equalTo(self).offset(15);
            make.width.height.mas_equalTo(50);
            make.width.height.mas_equalTo(50);
        }];
        
        [self addSubview:self.categoryLabel];
        [self.categoryLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.iconImage.mas_right).offset(10);
            make.top.equalTo(self.iconImage);
            make.right.equalTo(self).offset(-15);
            make.height.mas_equalTo(20);
        }];
        
        [self addSubview:self.descriptionLabel];
        [self.descriptionLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.iconImage.mas_right).offset(10);
            make.top.equalTo(self.categoryLabel.mas_bottom).offset(5);
            make.right.equalTo(self).offset(-15);
            make.height.mas_equalTo(20);
        }];
        
        
    }
    return self;

}

-(void)setIndexPath:(NSIndexPath *)indexPath{
    _indexPath = indexPath;

}

-(UIImageView *)iconImage{

    if (_iconImage == nil) {
        _iconImage = [[UIImageView alloc]initWithImage:[UIImage imageNamed:@"default_headImage"]];
        _iconImage.layer.cornerRadius = 25;
        _iconImage.clipsToBounds = YES;
    }
    return _iconImage;
}

-(UILabel *)categoryLabel{

    if (_categoryLabel == nil) {
        _categoryLabel = [UILabel new];
        _categoryLabel.text = @"类别类别";
        _categoryLabel.textColor = [UIColor colorWithHexString:@"#2d333a"];
        _categoryLabel.font = [UIFont systemFontOfSize:15];
    }
    return _categoryLabel;
}

-(UILabel *)descriptionLabel{
    if (_descriptionLabel == nil) {
        _descriptionLabel = [UILabel new];
        _descriptionLabel.text = @"类别";
        _descriptionLabel.textColor = [UIColor colorWithHexString:@"#4f5965"];
        _descriptionLabel.font = [UIFont systemFontOfSize:13];
    }
    return  _descriptionLabel;
}

@end
