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
        
        [self addSubview:self.tailLabel];
        [self.tailLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.centerY.equalTo(self);
            make.right.equalTo(self).offset(-30);
            make.width.mas_equalTo(150);
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

-(UILabel *)tailLabel{
    if (_tailLabel == nil) {
        _tailLabel = [UILabel new];
        _tailLabel.font = [UIFont systemFontOfSize:13];
        _tailLabel.textColor = [UIColor colorWithHexColor:@"#8f959c"];
        _tailLabel.textAlignment = NSTextAlignmentRight;
    }
    return _tailLabel;
}






-(void)setIndexPath:(NSIndexPath *)indexPath{
    _indexPath = indexPath;
    if (indexPath.section == 1) {
        
       
    }
}

@end
