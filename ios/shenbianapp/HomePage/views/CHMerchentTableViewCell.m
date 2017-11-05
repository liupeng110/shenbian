//
//  CHMerchentTableViewCell.m
//  shenbianapp
//
//  Created by book on 2017/9/3.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import "CHMerchentTableViewCell.h"

@interface CHMerchentTableViewCell ()

@property(nonatomic,strong)UIImageView *coverImageView;

@property(nonatomic,strong)UIImageView *ratingImageView;
@property(nonatomic,strong)UILabel *nameLabel;
@property(nonatomic,strong)UILabel *contentLabel;
@property(nonatomic,strong)UILabel *ratingLabel;
@property(nonatomic,strong)UILabel *soldOutLabel;
@property(nonatomic,strong)UILabel *tagName;
@property(nonatomic,strong)UILabel *distanceLabel;
@property(nonatomic,strong)UIImageView *distanceImageView;

@end

@implementation CHMerchentTableViewCell


- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier {

    if (self = [super initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier ]) {
       
        [self addSubview:self.coverImageView];
        [self.coverImageView mas_updateConstraints:^(MASConstraintMaker *make) {
            make.left.top.equalTo(self).offset(20);
            make.bottom.equalTo(self).offset(-20);
            make.width.mas_equalTo(120);
        }];
        
        [self addSubview:self.ratingImageView];
        [self.ratingImageView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.coverImageView.mas_right).offset(12);
            make.top.equalTo(self).offset(15);
            make.width.height.mas_equalTo(13);
        }];
        
        [self addSubview:self.ratingLabel];
        [self.ratingLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.ratingImageView.mas_right).offset(5);
            make.top.equalTo(self).offset(12);
            make.width.mas_equalTo(80);
            make.height.mas_equalTo(20);
        }];
        
        [self addSubview:self.distanceLabel];
        [self.distanceLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(self).offset(15);
            make.right.equalTo(self).offset(-15);
            make.width.mas_equalTo(60);
            make.height.mas_equalTo(20);
        }];
        
        [self addSubview:self.distanceImageView];
        [self.distanceImageView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(self).offset(18);
            make.right.equalTo(self.distanceLabel.mas_left).offset(-8);
            make.height.mas_equalTo(12);
            make.width.mas_equalTo(10);
        }];
        
        [self addSubview:self.tagName];
        [self.tagName mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.coverImageView.mas_right).offset(15);
            make.bottom.equalTo(self).offset(-18);
            make.width.mas_equalTo(60);
            make.height.mas_equalTo(24);
        }];
        
        [self addSubview:self.soldOutLabel];
        [self.soldOutLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.bottom.equalTo(self).offset(-22);
            make.right.equalTo(self).offset(-15);
            make.width.mas_equalTo(80);
            make.height.mas_equalTo(20);
        }];
        
        [self addSubview:self.nameLabel];
        [self.nameLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.coverImageView.mas_right).offset(12);
            make.top.equalTo(self.ratingImageView.mas_bottom).offset(8);
            make.width.mas_equalTo(80);
            make.height.mas_equalTo(20);
        }];
        
        [self addSubview:self.contentLabel];
        [self.contentLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(self.nameLabel.mas_bottom).offset(0);
            make.left.equalTo(self.coverImageView.mas_right).offset(12);
            make.width.mas_equalTo(195);
            make.height.mas_equalTo(20);
        }];
    
       
    }
    return self;
}


-(UILabel *)ratingLabel{
    if (_ratingLabel == nil) {
        _ratingLabel = [[UILabel alloc]init];
        _ratingLabel.font = [UIFont fontWithName:@"PingFangSC-Regular" size:13];
        _ratingLabel.textColor = [UIColor colorWithHexString:@"#ffd332"];
        _ratingLabel.layer.cornerRadius = 10;
    }
   return  _ratingLabel;
}

-(UIImageView *)coverImageView{

    if (_coverImageView == nil) {
        _coverImageView = [[UIImageView alloc]init];
        _coverImageView.contentMode = UIViewContentModeScaleAspectFill;
        _coverImageView.image = [UIImage imageNamed:@"sy_sj_cover"];
        _coverImageView.clipsToBounds = YES;
    }
    return _coverImageView;
}

-(UIImageView *)ratingImageView{

    if (_ratingImageView == nil) {
        _ratingImageView = [[UIImageView alloc]init];
        _ratingImageView.contentMode = UIViewContentModeScaleAspectFill;
        _ratingImageView.image = [UIImage imageNamed:@"syxh_xx"];
    }
    
    return _ratingImageView;
}

-(UILabel *)distanceLabel{

    if (_distanceLabel == nil) {
        _distanceLabel = [[UILabel alloc]init];
        _distanceLabel.textColor = [UIColor colorWithHexString:@"#8f959c"];
        _distanceLabel.font = [UIFont fontWithName:@"PingFangSC-Regular" size:13];
    }
    return _distanceLabel;
}

-(UIImageView *)distanceImageView{

    if (_distanceImageView == nil) {
        _distanceImageView = [[UIImageView alloc]initWithImage:[UIImage imageNamed:@"syxh_dw"]];
        
    }
    return _distanceImageView;
}

-(UILabel *)tagName{

    if (_tagName == nil) {
        _tagName = [[UILabel alloc]init];
        _tagName.backgroundColor = [UIColor colorWithHexString:@"#f0f0f0"];
        _tagName.layer.cornerRadius = 12;
        _tagName.font = [UIFont fontWithName:@"PingFangSC-Regular" size:13];
        _tagName.clipsToBounds = YES;
        _tagName.textAlignment = NSTextAlignmentCenter;
    }
    return _tagName;
}

-(UILabel *)soldOutLabel{
    if (_soldOutLabel == nil) {
        _soldOutLabel = [UILabel new];
        _soldOutLabel.textColor = [UIColor colorWithHexString:@"#8f959c"];
        _soldOutLabel.font = [UIFont fontWithName:@"PingFangSC-Regular" size:13];
    }
    return _soldOutLabel;
}

-(UILabel *)nameLabel{
    if (_nameLabel == nil) {
        _nameLabel = [UILabel new];
        _nameLabel.textColor = [UIColor colorWithHexString:@"#2d333a"];
        _nameLabel.font = [UIFont fontWithName:@"PingFangSC-Regular" size:15];
        
    }
    return _nameLabel;
}

-(UILabel *)contentLabel{

    if (_contentLabel == nil) {
        _contentLabel = [[UILabel alloc]init];
        _contentLabel.textColor = [UIColor colorWithHexString:@"#4f5965"];
        _contentLabel.font = [UIFont fontWithName:@"PingFangSC-Regular" size:13];
    }
    return _contentLabel;
}
-(void)setModel:(CHMerchentModel *)model{
    
    [self.coverImageView setImageWithURL:[NSURL URLWithString:model.iconUrl] placeholder:[UIImage imageNamed:model.placeHolder]];
    
    self.ratingLabel.text = model.rating;
    self.distanceLabel.text = [NSString stringWithFormat:@"%.fm",model.distance];
    self.ratingLabel.text = [NSString stringWithFormat:@"%@",model.rating];
    self.nameLabel.text = model.merchentName;
    self.contentLabel.text = model.content;
    self.soldOutLabel.text = [NSString stringWithFormat:@"已售：%ld件",model.slodOut];
    self.tagName.text = [NSString stringWithFormat:@"%@",model.tagName];
}
@end
