//
//  CHMyOrderTableViewCell.m
//  shenbianapp
//
//  Created by book on 2017/11/10.
//  Copyright © 2017 helinkeji. All rights reserved.
//

#import "CHMyOrderTableViewCell.h"

@interface CHMyOrderTableViewCell()

@property(nonatomic,strong)UIImageView *headImageView;
@property(nonatomic,strong)UILabel *titleLabel;
@property(nonatomic,strong)UILabel *contentLabel;
@property(nonatomic,strong)UILabel *timeLabel;
@property(nonatomic,strong)UIButton *payStatus;
@end


@implementation CHMyOrderTableViewCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier{

    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        [self addSubview:self.headImageView];
        [self.headImageView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self).offset(15);
            make.centerY.equalTo(self);
            make.width.mas_equalTo(120);
            make.height.mas_equalTo(90);
        }];
        [self addSubview:self.titleLabel];
        [self.titleLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.headImageView.mas_right).offset(10);
            make.top.equalTo(self.headImageView).offset(3);
            make.width.mas_equalTo(200);
            make.height.mas_equalTo(20);
        }];
        
        [self addSubview:self.contentLabel];
        [self.contentLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.headImageView.mas_right).offset(10);
            make.top.equalTo(self.titleLabel.mas_bottom);
            make.right.equalTo(self).offset(-15);
            make.height.mas_equalTo(50);
        }];
        
        [self addSubview:self.timeLabel];
        [self.timeLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(self.titleLabel);
            make.right.equalTo(self).offset(-15);
            make.width.mas_equalTo(150);
            make.height.mas_equalTo(20);
        }];
        
        [self addSubview:self.payStatus];
        [self.payStatus mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(self.contentLabel.mas_bottom).offset(3);
            make.right.equalTo(self).offset(-15);
            make.width.mas_equalTo(60);
            make.height.mas_equalTo(20);
        }];
    }
    return self;
}

-(UIImageView *)headImageView{

    if (_headImageView == nil) {
        _headImageView = [[UIImageView alloc]initWithImage:[UIImage imageNamed:@"sy_sj_cover"]];
        _headImageView.contentMode = UIViewContentModeScaleAspectFit;
        _headImageView.clipsToBounds = YES;
        _headImageView.layer.cornerRadius = 2;
    }
    
    return _headImageView;
}

-(UILabel *)titleLabel{

    if (_titleLabel == nil) {
        _titleLabel = [UILabel new];
        _titleLabel.font = [UIFont systemFontOfSize:15];
        _titleLabel.textColor = [UIColor colorWithHexColor:@"#2d333a"];
        _titleLabel.text = @"服务标题";
    }
    return _titleLabel;
}

-(UILabel *)contentLabel{

    if (_contentLabel == nil) {
        _contentLabel = [UILabel new];
        _contentLabel.font = [UIFont systemFontOfSize:13];
        _contentLabel.textColor = [UIColor colorWithHexColor:@"#4f5965"];
        _contentLabel.numberOfLines = 0;
        _contentLabel.text = @"我是内容啊，我是内容，我是内容啊，我是内容，我是内容啊，我是内容，我是内容啊，我是内容";
    }
    return _contentLabel;
}

-(UILabel *)timeLabel{
    
    if (_timeLabel == nil) {
        _timeLabel = [UILabel new];
        _timeLabel.text = @"8月6日";
        _timeLabel.font = [UIFont systemFontOfSize:10];
        _timeLabel.textAlignment = NSTextAlignmentRight;
        _timeLabel.textColor = [UIColor colorWithHexColor:@"#a2a5aa"];
    }
    return _timeLabel;
}

-(UIButton *)payStatus{
    
    if (_payStatus == nil) {
        _payStatus = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_payStatus setTitle:@"已支付" forState:(UIControlStateNormal)];
        _payStatus.titleLabel.font = [UIFont systemFontOfSize:13];
        _payStatus.backgroundColor = [UIColor colorWithHexColor:@"#009698"];
        _payStatus.layer.cornerRadius = 3;
    }
    return _payStatus;
}

-(void)setDataDic:(NSDictionary *)dataDic{
    NSString *coverImageUrl = [dataDic objectForKey:@"homeUrl"];
    [self.headImageView setImageWithURL:[NSURL URLWithString:coverImageUrl] placeholder:[UIImage imageNamed:@"default_headImage"]];
    self.titleLabel.text = [dataDic objectForKey:@"userName"];
    self.contentLabel.text = [dataDic objectForKey:@"serviceDescription"];
    NSInteger travel = [[dataDic objectForKey:@"createTime"] integerValue];
    NSDate *date = [NSDate dateWithTimeIntervalSince1970:travel/1000];
    NSDateFormatter *formatter = [[NSDateFormatter alloc]init];
    [formatter setDateFormat:@"yyyy-MM-dd HH:mm:ss"];
    self.timeLabel.text = [NSString stringWithFormat:@"%@",[formatter stringFromDate:date]];
    NSInteger payStatus = [[dataDic objectForKey:@"paymentStatus"] integerValue];
    switch (payStatus) {
        case 0:
            [self.payStatus  setTitle:@"未支付" forState:(UIControlStateNormal)];
            break;
        case 1:
            [self.payStatus  setTitle:@"已支付" forState:(UIControlStateNormal)];
            break;
        default:
            [self.payStatus  setTitle:@"支付失败" forState:(UIControlStateNormal)];
            break;
    }
}

@end
