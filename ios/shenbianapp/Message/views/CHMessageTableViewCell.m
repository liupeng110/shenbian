//
//  CHMessageTableViewCell.m
//  shenbianapp
//
//  Created by book on 2017/10/28.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import "CHMessageTableViewCell.h"

@interface CHMessageTableViewCell ()
@property(nonatomic,strong)UIImageView *headImage;
@property(nonatomic,strong)UILabel *userNameLabel;
@property(nonatomic,strong)UILabel *briefMsgLabel;
@property(nonatomic,strong)UILabel *lastTimeLabel;

@end

@implementation CHMessageTableViewCell

-(instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier{
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        
        [self addSubview:self.headImage];
        [self.headImage mas_makeConstraints:^(MASConstraintMaker *make) {
            make.centerY.equalTo(self);
            make.left.equalTo(self).offset(15);
            make.width.height.mas_equalTo(50);
        }];
        
        [self addSubview:self.userNameLabel];
        [self.userNameLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.headImage.mas_right).offset(10);
            make.top.equalTo(self).offset(15);
            make.width.mas_equalTo(200);
            make.height.mas_equalTo(20);
        }];
        
        [self addSubview:self.briefMsgLabel];
        [self.briefMsgLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.headImage.mas_right).offset(10);
            make.top.equalTo(self.userNameLabel.mas_bottom).offset(5);
            make.width.mas_equalTo(200);
            make.right.equalTo(self).offset(-15);
        }];
        
        [self addSubview:self.lastTimeLabel];
        [self.lastTimeLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.right.equalTo(self).offset(-15);
            make.top.equalTo(self).offset(15);
            make.width.mas_equalTo(100);
            make.height.mas_equalTo(20);
        }];
    }
    
    return self;
}

- (void)setModel:(CHMessageModel *)model{
    NSURL *headUrl = [NSURL URLWithString:model.headUrl];
    [self.headImage setImageWithURL:headUrl placeholder:[UIImage imageNamed:@"sy_sj_cover"]];
    self.userNameLabel.text = model.userName;
    self.briefMsgLabel.text = model.briefMessage;
    self.lastTimeLabel.text = model.lastTime;
    switch (model.messageType) {
        case MessageTypeChat:
            
            break;
        case MessageTypeOrder:
            self.userNameLabel.text = @"订单";
            break;
            
        case MessageTypeFocus:
            self.userNameLabel.text = @"已关注";
            break;
            
  
        default:
            break;
    }
}


-(UIImageView *)headImage{

    if (_headImage == nil) {
        _headImage = [[UIImageView alloc]initWithImage:[UIImage imageNamed:@"sy_sj_cover"]];
        _headImage.layer.cornerRadius = 25;
        _headImage.clipsToBounds = YES;
    }

    return _headImage;
}

-(UILabel *)userNameLabel{

    if (_userNameLabel == nil){
        _userNameLabel = [[UILabel alloc]init];
        _userNameLabel.text = @"用户名";
        _userNameLabel.font = [UIFont systemFontOfSize:15];
        _userNameLabel.textColor = [UIColor colorWithHexColor:@"#2d333a"];
    }
    return _userNameLabel;
}

-(UILabel *)briefMsgLabel{

    if (_briefMsgLabel == nil) {
        _briefMsgLabel = [[UILabel alloc]init];
        _briefMsgLabel.font = [UIFont systemFontOfSize:15];
        _briefMsgLabel.text = @"简短消息简单消息";
        _briefMsgLabel.textColor = [UIColor colorWithHexColor:@"#2d333a"];
    }
    return _briefMsgLabel;

}

-(UILabel *)lastTimeLabel{

    if (_lastTimeLabel == nil) {
        _lastTimeLabel = [UILabel new];
        _lastTimeLabel.text = @"00:00";
        _lastTimeLabel.textColor = [UIColor colorWithHexColor:@"#8f959c"];
        _lastTimeLabel.font = [UIFont systemFontOfSize:15];
    }
    return _lastTimeLabel;
}
@end
