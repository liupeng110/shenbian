//
//  HomeNavView.m
//  Miaohi
//
//  Created by 杨绍智 on 17/5/8.
//  Copyright © 2017年 haiqiu. All rights reserved.
//

#import "HomeNavView.h"

@interface HomeNavView ()<UICollectionViewDelegate,UICollectionViewDataSource,UICollectionViewDelegateFlowLayout>
@property(nonatomic,strong)UIButton *searchButton;
@property(nonatomic,strong)UICollectionView *quikCollectionView;
@property(nonatomic,strong)UIButton *shoppingCart;

@end

@implementation HomeNavView
- (instancetype)initWithFrame:(CGRect)frame{
    if (self = [super initWithFrame:frame]) {
        self.backgroundColor = [UIColor colorWithHexColor:@"#008e8f"];
        [self addSubview:self.locationButton];
        [self addSubview:self.searchButton];
        
        [self addSubview:self.shoppingCart];
        [self.shoppingCart mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(self);
            make.right.equalTo(self).offset(-2);
            make.width.height.mas_equalTo(40);
        }];
        
        [self addSubview:self.quikCollectionView];
        [self.quikCollectionView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self).offset(10);
            make.right.equalTo(self).offset(-10);
            make.bottom.equalTo(self).offset(-5);
            make.top.equalTo(self.searchButton.mas_bottom).offset(5);
        }];
        [RACObserve(self, quikSearchList) subscribeNext:^(id x) {
            if (x) {
                [self.quikCollectionView reloadData];
            }
        }];
    }
    return self;
}

-(UIButton *)locationButton{
    if (_locationButton == nil) {
        _locationButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        _locationButton.frame = CGRectMake(20, 0, 40, 40);
        [_locationButton setImage:[UIImage imageNamed:@"sy_dw"] forState:(UIControlStateNormal)];
        [_locationButton setTitle:@"北京" forState:(UIControlStateNormal)];
        _locationButton.titleLabel.font = [UIFont systemFontOfSize:15];
        _locationButton.titleEdgeInsets = UIEdgeInsetsMake(0, -10, 0, 0);
        _locationButton.imageEdgeInsets = UIEdgeInsetsMake(0, -18, 0, 0);
    }
    return _locationButton;
}

-(UIButton *)searchButton{
    if (_searchButton == nil) {
        _searchButton = [[UIButton alloc]initWithFrame:(CGRectMake(10, 40, kScreenWidth - 20, 35))];
        _searchButton.backgroundColor = [UIColor whiteColor];
        [_searchButton setImage:[UIImage imageNamed:@"sy_ss"] forState:(UIControlStateNormal)];
        [_searchButton setTitle:@"搜索" forState:(UIControlStateNormal)];
        [_searchButton setTitleColor:[UIColor grayColor] forState:(UIControlStateNormal)];
        _searchButton.titleEdgeInsets = UIEdgeInsetsMake(0, 10, 0, 0);
        _searchButton.titleLabel.font = [UIFont systemFontOfSize:15];
    }
    return _searchButton;
}

- (UIButton*)shoppingCart{

    if (_shoppingCart == nil) {
        _shoppingCart = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_shoppingCart setImage:[UIImage imageNamed:@"sy_gwc"] forState:(UIControlStateNormal)];
    }
    return _shoppingCart;
}

-(UICollectionView *)quikCollectionView{

    if (_quikCollectionView == nil) {
        UICollectionViewFlowLayout *flowLayout = [[UICollectionViewFlowLayout alloc]init];
        flowLayout.scrollDirection = UICollectionViewScrollDirectionHorizontal;
        _quikCollectionView = [[UICollectionView alloc]initWithFrame:(CGRectZero) collectionViewLayout:flowLayout];
        _quikCollectionView.delegate = self;
        _quikCollectionView.dataSource = self;
        [_quikCollectionView registerClass:[UICollectionViewCell class] forCellWithReuseIdentifier:@"quikCell"];
        _quikCollectionView.backgroundColor = [UIColor clearColor];
        _quikCollectionView.showsHorizontalScrollIndicator = NO;
    }
    return _quikCollectionView;
}

-(NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section{

   return  self.quikSearchList.count;
}

-(UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath{

    UICollectionViewCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"quikCell" forIndexPath:indexPath];
    UILabel *label = cell.contentView.subviews.firstObject;
    if (label == nil) {
        label = [[UILabel alloc]init];
        label.font = [UIFont systemFontOfSize:12];
        label.textColor = [UIColor whiteColor];
        [cell.contentView addSubview:label];
        label.frame= cell.contentView.bounds;
    } 
    NSString *name = [self.quikSearchList objectAtIndex:indexPath.row];
    label.text= name;
    return cell;
}

-(CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout *)collectionViewLayout sizeForItemAtIndexPath:(NSIndexPath *)indexPath{
    NSString *name = [self.quikSearchList objectAtIndex:indexPath.row];
    CGSize size = [name sizeWithAttributes:@{NSFontAttributeName:[UIFont systemFontOfSize:14]}];
    return size;
}

@end
