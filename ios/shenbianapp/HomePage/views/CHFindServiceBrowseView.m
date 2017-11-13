//
//  CHFindServiceBrowseView.m
//  shenbianapp
//
//  Created by book on 2017/11/4.
//  Copyright © 2017年 helinkeji. All rights reserved.
//

#import "CHFindServiceBrowseView.h"

@interface CHFindServiceBrowseView ()<UICollectionViewDelegate,UICollectionViewDataSource,UICollectionViewDelegateFlowLayout>
@property(nonatomic,strong)UICollectionView *categoryCollection;
@property(nonatomic,assign)BOOL isfirstTime;

@end

@implementation CHFindServiceBrowseView

-(instancetype)initWithFrame:(CGRect)frame{
    
    if (self = [super initWithFrame:frame]) {

        [self addSubview:self.categoryCollection];
        [self.categoryCollection mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.bottom.equalTo(self);
            make.left.equalTo(self).offset(15);
            make.right.equalTo(self).offset(-15);
        }];
    }
    
    return self;
}
-(UICollectionView *)categoryCollection{
    
    if (_categoryCollection == nil) {
        UICollectionViewFlowLayout *layout = [[UICollectionViewFlowLayout alloc]init];
        layout.minimumInteritemSpacing = 20;
        layout.minimumInteritemSpacing = 10;
        layout.scrollDirection = UICollectionViewScrollDirectionHorizontal;
        layout.itemSize = CGSizeMake(160, 300);
        _categoryCollection = [[UICollectionView alloc]initWithFrame:(CGRectZero) collectionViewLayout:layout];
        _categoryCollection.delegate =  self;
        _categoryCollection.dataSource = self;
        [_categoryCollection registerClass:[UICollectionViewCell class] forCellWithReuseIdentifier:@"categoryCell"];
        _categoryCollection.backgroundColor = [UIColor clearColor];
        _categoryCollection.showsHorizontalScrollIndicator = NO;
    }
    return _categoryCollection;
}

-(NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section{
    return self.browseItemList.count;
}

-(UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath{
    UICollectionViewCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"categoryCell" forIndexPath:indexPath];
    UIImageView *imageView = cell.contentView.subviews.firstObject;
    if (imageView == nil) {
        imageView = [[UIImageView alloc]initWithImage:[UIImage imageNamed:@"default_cover"]];
        imageView.layer.cornerRadius = 5;
        imageView.clipsToBounds = YES;
        [cell.contentView addSubview:imageView];
        [imageView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.left.right.equalTo(cell.contentView);
            make.bottom.equalTo(cell.contentView).offset(-80);
        }];
        
        UILabel *priceLabel = [UILabel new];
        priceLabel.text = @"￥168";
        priceLabel.textColor = [UIColor colorWithHexColor:@"#2d333a"];
        priceLabel.font = [UIFont systemFontOfSize:15];
        [cell.contentView addSubview:priceLabel];
        [priceLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(imageView.mas_bottom).offset(5);
            make.left.equalTo(cell.contentView);
            make.width.mas_equalTo(80);
            make.height.mas_equalTo(20);
        }];
        
        UILabel *locationLabel = [UILabel new];
        locationLabel.text = @"中关村";
        locationLabel.textColor = [UIColor colorWithHexColor:@"#8f959c"];
        locationLabel.font = [UIFont systemFontOfSize:12];
        [cell.contentView addSubview:locationLabel];
        [locationLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(priceLabel);
            make.right.equalTo(cell.contentView);
            make.width.mas_equalTo(80);
            make.height.mas_equalTo(20);
        }];
        
        UILabel *serviceTitle = [UILabel new];
        serviceTitle.text = @"服务标题服务标题服务标题服务标题";
        serviceTitle.textColor = [UIColor colorWithHexColor:@"#2d333a"];
        serviceTitle.font = [UIFont systemFontOfSize:15];
        [cell.contentView addSubview:serviceTitle];
        [serviceTitle mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(priceLabel.mas_bottom).offset(2);
            make.left.right.equalTo(cell.contentView);
            make.height.mas_equalTo(20);
        }];
        UIImageView *starImageView = [[UIImageView alloc]initWithImage:[UIImage imageNamed:@"syxh_xx"]];
        [cell.contentView addSubview:starImageView];
        [starImageView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(serviceTitle.mas_bottom).offset(5);
            make.left.equalTo(cell.contentView);
            make.width.mas_equalTo(15);
        }];
        
        UILabel *ratingLabel = [UILabel new];
        ratingLabel.text = [NSString stringWithFormat:@"%@(%@)",@"4.8",@"168"];
        ratingLabel.textColor = [UIColor colorWithHexColor:@"#ffd332"];
        ratingLabel.font = [UIFont systemFontOfSize:13];
        [cell.contentView addSubview:ratingLabel];
        [ratingLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(starImageView.mas_right).offset(5);
            make.right.equalTo(cell.contentView);
            make.top.equalTo(serviceTitle.mas_bottom).offset(2);
            make.height.mas_equalTo(15);
        }];
        
        UIImageView *praiseImageV = [[UIImageView alloc]initWithImage:[UIImage imageNamed:@"zfw_unselected"]];
        [imageView addSubview:praiseImageV];
        [praiseImageV mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(imageView).offset(15);
            make.right.equalTo(imageView).offset(-15);
        }];
    }

    return cell;
}

-(void)collectionView:(UICollectionView *)collectionView didSelectItemAtIndexPath:(NSIndexPath *)indexPath{

    if (self.selectService) {
        self.selectService(@"");
    }
}

@end
