//
//  CHServiceTableViewCell.m
//  shenbianapp
//
//  Created by book on 2017/9/16.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import "CHServiceTableViewCell.h"
@interface CHServiceTableViewCell()
@property(nonatomic,strong)UIImageView *headImageView;
@property(nonatomic,strong)UILabel *nameLabel;
@property(nonatomic,strong)UILabel *distanceLabel;
@property(nonatomic,strong)UIImageView *distanceImageV;
@property(nonatomic,strong)UILabel *serviceTitleLabel;
@property(nonatomic,strong)UITextView *serviceContentLabel;

@property(nonatomic,strong) UILabel *priceLabel;
@property(nonatomic,strong) UIButton *shopCartButton;


@property(nonatomic,strong) UILabel *serviceTypeLabel;
@property(nonatomic,strong) UILabel *serviceTypeDetailsLabel;

@property(nonatomic,strong) UILabel *commentCountLabel;
@property(nonatomic,strong) UIImageView *commentRateImageV;
@property(nonatomic,strong) UILabel *commentValueLabel;

@property(nonatomic,strong) UIScrollView *recommendScrollView;
@property(nonatomic,strong) UILabel *recommendLabel;
@property(nonatomic,strong) UIImageView *recommentImageV;

@property(nonatomic,strong) NSArray *recommentArray;
@end

@implementation CHServiceTableViewCell

-(instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier{

    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        self.selectionStyle = UITableViewCellSelectionStyleNone;
        self.recommentArray = @[@"",@"",@""];
    }
    return self;
}

-(void)setIndexPath:(NSIndexPath *)indexPath{

    _indexPath = indexPath;
    
    switch (_indexPath.section) {
        case 0:{
            [self addSubview:self.headImageView];
            [self.headImageView mas_makeConstraints:^(MASConstraintMaker *make) {
                make.left.equalTo(self).offset(15);
                make.top.equalTo(self).offset(10);
                make.width.height.mas_equalTo(40);
            }];
            
            [self addSubview:self.nameLabel];
            [self.nameLabel mas_makeConstraints:^(MASConstraintMaker *make) {
                make.left.equalTo(self.headImageView.mas_right).offset(8);
                make.top.equalTo(self).offset(10);
                make.height.mas_equalTo(20);
                make.width.mas_equalTo(100);
                
            }];
            
            [self addSubview:self.distanceImageV];
            [self.distanceImageV mas_makeConstraints:^(MASConstraintMaker *make) {
                make.left.equalTo(self.headImageView.mas_right).offset(8);
                make.top.equalTo(self.nameLabel.mas_bottom).offset(5);
                make.height.mas_equalTo(12);
                make.width.mas_equalTo(10);
            }];
            
            [self addSubview:self.distanceLabel];
            [self.distanceLabel mas_makeConstraints:^(MASConstraintMaker *make) {
                make.left.equalTo(self.distanceImageV.mas_right).offset(8);
                make.top.equalTo(self.nameLabel.mas_bottom).offset(0);
                make.width.mas_equalTo(80);
                make.height.mas_equalTo(20);
            }];
            
            [self addSubview:self.serviceTitleLabel];
            [self.serviceTitleLabel mas_makeConstraints:^(MASConstraintMaker *make) {
                make.left.equalTo(self).offset(15);
                make.top.equalTo(self.headImageView.mas_bottom).offset(15);
                make.height.mas_equalTo(20);
                make.width.mas_equalTo(100);
            }];
            
            [self addSubview:self.serviceContentLabel];
            [self.serviceContentLabel mas_makeConstraints:^(MASConstraintMaker *make) {
                make.left.equalTo(self).offset(15);
                make.top.equalTo(self.serviceTitleLabel.mas_bottom);
                make.right.equalTo(self).offset(-15);
                make.height.mas_equalTo(44);
            }];
            
        }break;
            
        case 1:{
        
            switch (indexPath.row) {
                case 0:
                {
                    [self addSubview:self.serviceTypeLabel];
                    [self.serviceTypeLabel mas_makeConstraints:^(MASConstraintMaker *make) {
                        make.left.equalTo(self).offset(15);
                        make.top.equalTo(self).offset(5);
                        make.height.mas_equalTo(20);
                        make.width.mas_equalTo(80);
                    }];
                    
                    [self addSubview:self.serviceTypeDetailsLabel];
                    [self.serviceTypeDetailsLabel mas_makeConstraints:^(MASConstraintMaker *make) {
                        make.left.equalTo(self).offset(15);
                        make.top.equalTo(self.serviceTypeLabel.mas_bottom).offset(5);
                        make.height.mas_equalTo(20);
                        make.width.mas_equalTo(80);
                    }];
                }
                    break;
                case 1:{
                    [self addSubview:self.commentCountLabel];
                    [self.commentCountLabel mas_makeConstraints:^(MASConstraintMaker *make) {
                        make.left.equalTo(self).offset(15);
                        make.centerY.equalTo(self);
                        make.width.mas_equalTo(60);
                        make.height.mas_equalTo(20);
                    }];
                    
                    [self addSubview:self.commentRateImageV];
                    [self.commentRateImageV mas_makeConstraints:^(MASConstraintMaker *make) {
                        
                        make.centerY.equalTo(self);
                        make.left.equalTo(self.commentCountLabel.mas_right).offset(16);
                        make.width.mas_equalTo(100);
                        make.height.mas_equalTo(20);
                    }];
                    
                    [self addSubview:self.commentValueLabel];
                    [self.commentValueLabel mas_makeConstraints:^(MASConstraintMaker *make) {
                        make.left.equalTo(self.commentRateImageV.mas_right).offset(8);
                        make.centerY.equalTo(self);
                        make.width.mas_equalTo(60);
                        make.height.mas_equalTo(20);
                    }];
                
                }
                default:
                    break;
            }
           

        }
            break;
            
        case 2:{
            [self addSubview:self.recommendLabel];
            [self.recommendLabel mas_makeConstraints:^(MASConstraintMaker *make) {
                make.top.equalTo(self).offset(5);
                make.left.equalTo(self).offset(15);
                make.height.mas_equalTo(20);
                make.width.mas_equalTo(100);
            }];
            
            [self addSubview:self.recommendScrollView];
            [self.recommendScrollView mas_makeConstraints:^(MASConstraintMaker *make) {
                make.left.equalTo(self).offset(15);
                make.right.equalTo(self).offset(-15);
                make.bottom.equalTo(self);
                make.top.equalTo(self).offset(30);
            }];
        }break;
            
            
        default:
            break;
    }

}



-(UIImageView *)headImageView{
    if (_headImageView == nil) {
        _headImageView = [[UIImageView alloc]initWithImage:[UIImage imageNamed:@"sy_sj_cover"]];
        _headImageView.layer.cornerRadius = 20;
        _headImageView.backgroundColor = [UIColor purpleColor];
        _headImageView.clipsToBounds = YES;
    }
    return _headImageView;
}

-(UILabel *)nameLabel{

    if (_nameLabel == nil) {
        _nameLabel = [UILabel new];
        _nameLabel.textColor = [UIColor colorWithHexString:@"#4f5965"];
        _nameLabel.text = @"身边号";
    }
    return  _nameLabel ;
}

-(UIImageView *)distanceImageV{

    if (_distanceImageV == nil) {
        _distanceImageV = [[UIImageView alloc]initWithImage:[UIImage imageNamed:@"fx_dw"]];
    }
    return _distanceImageV;
}

-(UILabel *)distanceLabel{

    if (_distanceLabel == nil) {
        _distanceLabel = [UILabel new];
        _distanceLabel.font = [UIFont systemFontOfSize:13];
        _distanceLabel.textColor = [UIColor colorWithHexString:@"#8f959c"];
        _distanceLabel.text = @"距离";
    }
    return _distanceLabel;
}

-(UILabel *)serviceTitleLabel{
    if (_serviceTitleLabel == nil) {
        _serviceTitleLabel = [UILabel new];
        _serviceTitleLabel.font = [UIFont systemFontOfSize:15];
        _serviceTitleLabel.textColor = [UIColor colorWithHexString:@"#2d333a"];
        _serviceTitleLabel.text = @"服务标题";
    }
    return _serviceTitleLabel;
}

-(UITextView *)serviceContentLabel{
    if (_serviceContentLabel == nil) {
        _serviceContentLabel = [UITextView new];
        _serviceContentLabel.font = [UIFont systemFontOfSize:13];
        _serviceContentLabel.textColor = [UIColor colorWithHexString:@"#4f5965"];
        _serviceContentLabel.text = @"服务内容我是内容呀呀我是内容呀呀我是内容呀呀我是";
    }
   return  _serviceContentLabel ;
}

-(UILabel *)serviceTypeLabel{

    if (_serviceTypeLabel == nil) {
        _serviceTypeLabel = [UILabel new];
        _serviceTypeLabel.text = @"服务类型";
        _serviceTypeLabel.font = [UIFont systemFontOfSize:15];
        _serviceTypeLabel.textColor = [UIColor colorWithHexString:@"#2d333a"];
        
    }
    return _serviceTypeLabel;
}

-(UILabel *)serviceTypeDetailsLabel{
    if (_serviceTypeDetailsLabel == nil) {
        _serviceTypeDetailsLabel = [UILabel new];
        _serviceTypeDetailsLabel.font = [UIFont systemFontOfSize:13];
        _serviceTypeDetailsLabel.textColor = [UIColor colorWithHexString:@"#4f5965"];
        _serviceTypeDetailsLabel.text = @"在线服务";
    }
    return _serviceTypeDetailsLabel;
}

-(UILabel *)priceLabel{

    if (_priceLabel == nil) {
        _priceLabel = [UILabel new];
        _priceLabel.text = @"￥100";
        _priceLabel.font = [UIFont systemFontOfSize:22];
        _priceLabel.textColor = [UIColor colorWithHexString:@"#009698"];
    }
    return _priceLabel;
}

-(UIButton *)shopCartButton{
    if (_shopCartButton == nil) {
        _shopCartButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_shopCartButton setTitle:@"加入购物车" forState:(UIControlStateNormal)];
        [_shopCartButton setTitleColor:[UIColor colorWithHexString:@"#009698"] forState:(UIControlStateNormal)];
        _shopCartButton.titleLabel.font = [UIFont systemFontOfSize:12];
        
        [_shopCartButton setImage:[UIImage imageNamed:@"spxq_gwc"] forState:(UIControlStateNormal)];
        _shopCartButton.titleEdgeInsets = UIEdgeInsetsMake(0, -40, -50, 0);
        @weakify(self);
        _shopCartButton.rac_command = [[RACCommand alloc]initWithSignalBlock:^RACSignal *(id input) {
            @strongify(self);
            if (self.clickAddShopCart) {
                self.clickAddShopCart();
            }
            return nil;
        }];
    }
    return _shopCartButton;
}

-(UILabel *)commentCountLabel{

    if (_commentCountLabel == nil) {
        _commentCountLabel = [UILabel new];
        _commentCountLabel.text = @"102 评论";
        _commentCountLabel.font = [UIFont systemFontOfSize:13];
        _commentCountLabel.textColor = [UIColor colorWithHexString:@"#4f5965"];
    }
   return  _commentCountLabel;

}


-(UIImageView *)commentRateImageV{

    if (_commentRateImageV == nil) {
        _commentRateImageV = [[UIImageView alloc]initWithImage:[UIImage imageNamed:@"syxh_xx"]];
        _commentRateImageV.contentMode = UIViewContentModeScaleAspectFit;
        
    }
    return _commentRateImageV;
}

-(UILabel *)commentValueLabel{
    if (_commentValueLabel == nil) {
        _commentValueLabel = [UILabel new];
        _commentValueLabel.font = [UIFont systemFontOfSize:13];
        _commentValueLabel.textColor = [UIColor colorWithHexString:@"#4f5965"];
        _commentValueLabel.text = @"(4.9)";
    }
    return _commentValueLabel;
}

-(UIScrollView *)recommendScrollView{
    
    if (_recommendScrollView == nil) {
        _recommendScrollView = [[UIScrollView alloc]init];
        for (int i = 0; i < 5; i++) {
            UIImageView *imageView = [[UIImageView alloc]initWithImage:[UIImage imageNamed:@"default_image"]];
            imageView.frame = CGRectMake( 123 * i + 15,15, 108, 80);
            [_recommendScrollView addSubview:imageView];
        }
        _recommendScrollView.contentSize = CGSizeMake(123 * 5 + 15 , 80);
    }
    
    return _recommendScrollView;
}

-(UILabel *)recommendLabel{

    if (_recommendLabel == nil) {
        _recommendLabel = [UILabel new];
        _recommendLabel.font = [UIFont systemFontOfSize:15];
        _recommendLabel.textColor = [UIColor colorWithHexString:@"#2d333a"];
        _recommendLabel.text = @"优质服务推荐";
    }
    return _recommendLabel;
}



-(void)setCellModel:(CHServiceCellModel *)cellModel{
    
    switch (_indexPath.row) {
        case 0:
            
            break;
        case 1:
            
            break;
        case 2:
            
            break;
        case 3:
        {
        
        }
            break;
        case 4:{
            NSInteger index = 0;
            for (NSString *url in self.recommentArray) {
                
                CGFloat space = 10;
                CGFloat sideSpace = 30;//左右两边边距
                CGFloat imgWidth = (kScreenWidth - sideSpace - space * 2) / 3 ;
                
                CGFloat left = (imgWidth + space) * index + 15;
                UIImageView *recommentImageView = [[UIImageView alloc]initWithFrame:(CGRectMake(left, 15, imgWidth, imgWidth * 0.8))];
                [recommentImageView setImageWithURL:[NSURL URLWithString:url] placeholder:[UIImage imageNamed:@"sy_sj_cover"]];
                [self.recommendScrollView addSubview:recommentImageView];

                index++;
            }
        }
            
        default:
            break;
    }
    
}


@end
