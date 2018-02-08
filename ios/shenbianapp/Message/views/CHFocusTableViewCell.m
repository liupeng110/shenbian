//
//  CHFocusTableViewCell.m
//  shenbianapp
//
//  Created by book on 2017/11/11.
//  Copyright © 2017 helinkeji. All rights reserved.
//

#import "CHFocusTableViewCell.h"

@interface CHFocusTableViewCell ()
@property(nonatomic,strong)UIImageView *headImageView;
@property(nonatomic,strong)UILabel *nameLabel;
@property(nonatomic,strong)UILabel *messageLabel;
@property(nonatomic,strong)UILabel *timeLabel;
@end

@implementation CHFocusTableViewCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier{
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        [self addSubview:self.headImageView];
        [self.headImageView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self).offset(15);
            make.centerY.equalTo(self);
            make.width.height.mas_equalTo(50);
        }];
        
        [self addSubview:self.nameLabel];
        [self.nameLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.headImageView.mas_right).offset(10);
            make.top.equalTo(self.headImageView).offset(3);
            make.width.mas_equalTo(200);
            make.height.mas_equalTo(20);
        }];
        
        [self addSubview:self.messageLabel];
        [self.messageLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.headImageView.mas_right).offset(10);
            make.top.equalTo(self.nameLabel.mas_bottom).offset(3);
            make.width.mas_equalTo(200);
            make.height.mas_equalTo(20);
        }];
        
        [self addSubview:self.timeLabel];
        [self.timeLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.right.equalTo(self).offset(-15);
            make.top.equalTo(self.nameLabel);
            make.width.mas_equalTo(150);
            make.height.mas_equalTo(20);
        }];
    }
    return self;
}

-(UIImageView *)headImageView{

    if (_headImageView == nil) {
        _headImageView = [[UIImageView alloc]initWithImage:[UIImage imageNamed:@"default_headImage"]];
        _headImageView.layer.cornerRadius = 25;
        _headImageView.clipsToBounds = YES;
    }
    return _headImageView;
}

-(UILabel *)nameLabel{

    if (_nameLabel == nil) {
        _nameLabel = [UILabel new];
        _nameLabel.font = [UIFont systemFontOfSize:15];
        _nameLabel.textColor = [UIColor colorWithHexColor:@"#2d333a"];
        _nameLabel.text = @"用户名";
    }
    
    return _nameLabel;
}

-(UILabel *)messageLabel{

    if (_messageLabel == nil) {
        _messageLabel = [UILabel new];
        _messageLabel.text = @"最新消息";
        _messageLabel.textColor = [UIColor colorWithHexColor:@"#2d333a"];
        _messageLabel.font = [UIFont systemFontOfSize:13];
    }
    
    return _messageLabel;
}

-(UILabel *)timeLabel{

    if (_timeLabel == nil) {
        _timeLabel = [UILabel new];
        _timeLabel.text = @"11:11";
        _timeLabel.font = [UIFont systemFontOfSize:13];
        _timeLabel.textColor = [UIColor colorWithHexColor:@"#a2a5aa"];
        _timeLabel.textAlignment = NSTextAlignmentRight;
    }
    return _timeLabel;
}

-(void)setUserDic:(NSDictionary *)userDic{
   [self.headImageView setImageWithURL:[NSURL URLWithString:userDic[@"userIcon"]] placeholder:[UIImage imageNamed:@"default_headImage"]];
    self.nameLabel.text = userDic[@"userName"];
    NSTimeInterval traval = [userDic[@"updateTime"] integerValue];
    NSDate *updateDate = [NSDate dateWithTimeIntervalSince1970:traval / 1000 ];
    NSDateFormatter *stampFormatter = [[NSDateFormatter alloc] init];
    [stampFormatter setDateFormat:@"YYYY-MM-dd HH:mm:ss"];
   
    self.timeLabel.text = [stampFormatter stringFromDate:updateDate];
}

@end
