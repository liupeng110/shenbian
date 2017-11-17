//
//  CHFindPeopleBrowseView.m
//  shenbianapp
//
//  Created by book on 2017/11/4.
//  Copyright © 2017年 helinkeji. All rights reserved.
//

#import "CHFindPeopleBrowseView.h"

@interface CHFindPeopleBrowseView ()<UICollectionViewDelegate,UICollectionViewDataSource,UICollectionViewDelegateFlowLayout>
@property(nonatomic,strong)UICollectionView *categoryCollection;

@end

@implementation CHFindPeopleBrowseView

-(instancetype)initWithFrame:(CGRect)frame{
    
    if (self = [super initWithFrame:frame]) {
                
        [self addSubview:self.categoryCollection];
        [self.categoryCollection mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(self).offset(10);
            make.bottom.equalTo(self);
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
        layout.minimumInteritemSpacing = 20;
        layout.scrollDirection = UICollectionViewScrollDirectionVertical;
        layout.itemSize = CGSizeMake(160, 210);
        _categoryCollection = [[UICollectionView alloc]initWithFrame:(CGRectZero) collectionViewLayout:layout];
        _categoryCollection.delegate =  self;
        _categoryCollection.dataSource = self;
        [_categoryCollection registerClass:[UICollectionViewCell class] forCellWithReuseIdentifier:@"categoryCell"];
        _categoryCollection.backgroundColor = [UIColor clearColor];
        _categoryCollection.showsVerticalScrollIndicator = NO;

    }
    return _categoryCollection;
}

-(NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section{
    return self.optimizedItemList.count;
}

-(UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath{
    UICollectionViewCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"categoryCell" forIndexPath:indexPath];
    UIImageView *imageView = cell.contentView.subviews.firstObject;
    if (imageView == nil) {
        imageView = [[UIImageView alloc]initWithImage:[UIImage imageNamed:@"default_image"]];
        imageView.layer.cornerRadius = 5;
        imageView.clipsToBounds = YES;
        [cell.contentView addSubview:imageView];
        [imageView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.left.right.equalTo(cell.contentView);
            make.height.mas_equalTo(110);
        }];
        
        UILabel *nameLabel = [UILabel new];
        nameLabel.text = @"我是人名";
        nameLabel.textColor = [UIColor colorWithHexColor:@"#2d333a"];
        nameLabel.font = [UIFont boldSystemFontOfSize:15];
        [cell.contentView addSubview:nameLabel];
        [nameLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(imageView.mas_bottom).offset(5);
            make.left.equalTo(cell.contentView);
            make.width.mas_equalTo(80);
            make.height.mas_equalTo(20);
        }];
        
        UIImageView *headImageV = [[UIImageView alloc]initWithImage:[UIImage imageNamed:@"default_headImage"]];
        headImageV.clipsToBounds = YES;
        headImageV.layer.cornerRadius = 20;
        headImageV.layer.borderWidth = 2;
        headImageV.layer.borderColor = [UIColor whiteColor].CGColor;
        [cell.contentView addSubview:headImageV];
        [headImageV mas_makeConstraints:^(MASConstraintMaker *make) {
            make.width.height.mas_equalTo(40);
            make.right.equalTo(cell.contentView);
            make.top.equalTo(imageView.mas_bottom).offset(-20);
        }];
        
        UILabel *locationLabel = [UILabel new];
        locationLabel.text = @"中关村";
        locationLabel.textColor = [UIColor colorWithHexColor:@"#8f959c"];
        locationLabel.font = [UIFont systemFontOfSize:12];
        [cell.contentView addSubview:locationLabel];
        [locationLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(nameLabel.mas_bottom);
            make.left.equalTo(cell.contentView);
            make.width.mas_equalTo(80);
            make.height.mas_equalTo(20);
        }];
        
        UILabel *serviceTitle = [UILabel new];
        serviceTitle.text = @"服务标题服务标题服务标题服务标题";
        serviceTitle.textColor = [UIColor colorWithHexColor:@"#2d333a"];
        serviceTitle.font = [UIFont systemFontOfSize:15];
        [cell.contentView addSubview:serviceTitle];
        [serviceTitle mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(locationLabel.mas_bottom).offset(2);
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
            make.height.mas_equalTo(20);
        }];
        
        UILabel *dealLabel = [UILabel new];
        dealLabel.text = [NSString stringWithFormat:@"成交%d单",168];
        dealLabel.textAlignment = NSTextAlignmentRight;
        dealLabel.textColor = [UIColor colorWithHexColor:@"#8f959c"];
        dealLabel.font = [UIFont systemFontOfSize:12];
        [cell.contentView addSubview:dealLabel];
        [dealLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(ratingLabel);
            make.right.equalTo(cell.contentView).offset(-15);
            make.width.mas_equalTo(80);
            make.height.mas_equalTo(20);
        }];
    }

    return cell;
}

-(void)collectionView:(UICollectionView *)collectionView didSelectItemAtIndexPath:(NSIndexPath *)indexPath{
    if (self.didSelectItem) {
        self.didSelectItem(@"");
    }

}

-(void)scrollViewWillBeginDragging:(UIScrollView *)scrollView{
    
    if (self.scrollViewWillBeginDragging) {
        self.scrollViewWillBeginDragging();
    }

}


@end