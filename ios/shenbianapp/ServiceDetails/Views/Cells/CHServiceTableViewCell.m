//
//  CHServiceTableViewCell.m
//  shenbianapp
//
//  Created by book on 2017/9/16.
//  Copyright © 2017 . All rights reserved.
//

#define RCloudHost @"http://ouyv8tyz1.bkt.clouddn.com/"

#import "CHServiceTableViewCell.h"
@interface CHServiceTableViewCell()<UIWebViewDelegate>
@property(nonatomic,strong)UIImageView *headImageView;
@property(nonatomic,strong)UILabel *nameLabel;
@property(nonatomic,strong)UILabel *distanceLabel;
@property(nonatomic,strong)UIImageView *distanceImageV;
@property(nonatomic,strong)UILabel *serviceTitleLabel;
@property(nonatomic,strong)UIWebView *serviceContentWeb;

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

@property(nonatomic,strong) UIActivityIndicatorView *indicatorView;
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
                make.right.equalTo(self).offset(-15);
                
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
                make.top.equalTo(self.headImageView.mas_bottom).offset(5);
                make.height.mas_equalTo(20);
                make.right.equalTo(self).offset(-15);
            }];
            
            [self addSubview:self.serviceContentWeb];
            [self.serviceContentWeb mas_makeConstraints:^(MASConstraintMaker *make) {
                make.left.equalTo(self).offset(15);
                make.top.equalTo(self.serviceTitleLabel.mas_bottom);
                make.right.equalTo(self).offset(-15);
                make.height.mas_equalTo(164);
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
                    
                    [self addSubview:self.commentValueLabel];
                    [self.commentValueLabel mas_makeConstraints:^(MASConstraintMaker *make) {
                        make.left.equalTo(self.commentCountLabel.mas_right).offset(70);
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

-(void)setCellModel:(CHServiceCellModel *)cellModel{
    
    _cellModel = cellModel;
    
    switch (_indexPath.section) {
        case 0:
        {
            if (_indexPath.row == 0) {
                self.nameLabel.text = cellModel.userName;
                [self.headImageView setImageWithURL:[NSURL URLWithString:cellModel.userIconUrl] placeholder:[UIImage imageNamed:@"sy_sj_cover"]];
                
                [GlobalData distacewithLocation:cellModel.locationStr result:^(NSString *distance) {
                    self.distanceLabel.text = distance;
                }];
                
                self.serviceTitleLabel.text = cellModel.serviceTitle;
                NSString *htmlString = cellModel.serviceContent;
                NSString *htmls = [NSString stringWithFormat:@"<html> \n"
                                   "<head> \n"
                                   "<style type=\"text/css\"> \n"
                                   "body {font-size:13px;}\n"
                                   "</style> \n"
                                   "</head> \n"
                                   "<body>"
                                   "<script type='text/javascript'>"
                                   "window.onload = function(){\n"
                                   "var $img = document.getElementsByTagName('img');\n"
                                   "for(var p in  $img){\n"
                                   " $img[p].style.width = '100%%';\n"
                                   "$img[p].style.height ='auto'\n"
                                   "}\n"
                                   "}"
                                   "</script>%@"
                                   "</body>"
                                   "</html>",htmlString];
                [self.serviceContentWeb loadHTMLString:htmls baseURL:nil];
            }
            
        }
            break;
            
        case 1:{
            switch (_indexPath.row) {
                case 0:
                {
                    switch (cellModel.serviceType) {
                        case 0:
                            self.serviceTypeDetailsLabel.text = @"在线服务";
                            break;
                        case 1:
                            self.serviceTypeDetailsLabel.text = @"上门服务";
                            break;
                        case 2:
                            self.serviceTypeDetailsLabel.text = @"到店服务";
                            break;
                            
                        default:
                            self.serviceTypeDetailsLabel.text = @"在线服务";
                            break;
                    }
                }
                    break;
                case 1:{
                    self.commentCountLabel.text = [NSString stringWithFormat:@"%@ 评论",cellModel.commentCount];
                    CGSize size = [self.commentCountLabel sizeThatFits:(CGSizeMake(kScreenWidth, 20))];
                    [self.commentCountLabel mas_updateConstraints:^(MASConstraintMaker *make) {
                        make.width.mas_equalTo(size.width);
                    }];
                    
                    NSInteger starRating = cellModel.starRating.integerValue;
                    starRating = starRating == 0 ? 5 : starRating;
                    for (int i = 0; i < starRating; i++) {
                        UIImageView *imageView = [[UIImageView alloc]initWithImage:[UIImage imageNamed:@"syxh_xx"]];
                        [self addSubview:imageView];
                        [imageView mas_makeConstraints:^(MASConstraintMaker *make) {
                            make.left.equalTo(self.commentCountLabel.mas_right).offset(5 + 13 * i);
                            make.width.height.mas_equalTo(13);
                            make.centerY.equalTo(self);
                        }];
                        
                    }
                    
                    self.commentValueLabel.text = [NSString stringWithFormat:@"%@",cellModel.starRating];
                    
                    
                }break;
                    
                default:
                    break;
            }
            
            
        }break;
            
        case 2:{
            NSInteger recommendCount = cellModel.recommondList.count;
            for (int i = 0; i < recommendCount; i++) {
                UIImageView *imageView = [[UIImageView alloc]initWithImage:[UIImage imageNamed:@"default_image"]];
                imageView.frame = CGRectMake( 123 * i + 15,15, 108, 80);
                imageView.backgroundColor = [UIColor purpleColor];
                imageView.contentMode = UIViewContentModeScaleAspectFill;
                [self.recommendScrollView addSubview:imageView];
                NSDictionary *dic = cellModel.recommondList[2];
                NSString *urlString = [dic objectForKey:@"homeUrl"];
                [imageView setImageURL:[NSURL URLWithString:urlString]];
            }
            self.recommendScrollView.contentSize = CGSizeMake(123 * recommendCount + 15 , 80);
            
        }
            
            
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
        _nameLabel.text = @"用户名称";
    }
    return  _nameLabel;
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
        _distanceLabel.text = @"距离多少";
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

-(UIWebView *)serviceContentWeb{
    if (_serviceContentWeb == nil) {
        _serviceContentWeb = [UIWebView new];
        _serviceContentWeb.backgroundColor = [UIColor clearColor];
        _serviceContentWeb.delegate = self;
    }
    return  _serviceContentWeb ;
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


-(void)webViewDidStartLoad:(UIWebView *)webView{
    self.indicatorView = [[UIActivityIndicatorView alloc]initWithActivityIndicatorStyle:(UIActivityIndicatorViewStyleGray)];
    self.indicatorView.center = CGPointMake(self.serviceContentWeb.width/2.0, self.serviceContentWeb.height/2.0);
    [self.serviceContentWeb addSubview:self.indicatorView];
    [self.indicatorView startAnimating];
    
}

-(void)webViewDidFinishLoad:(UIWebView *)webView{
    
    [self.indicatorView stopAnimating];

}



@end
