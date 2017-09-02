//
//  CustomDiviceView.m
//  Miaohi
//
//  Created by 杨绍智 on 16/12/6.
//  Copyright © 2016年 haiqiu. All rights reserved.
//

#import "CustomDiviceView.h"
#import "CHCategoryCollectionViewCell.h"
@interface CustomDiviceView ()<UICollectionViewDelegate,UICollectionViewDataSource>
@property(nonatomic,strong) UICollectionView *itemCollectionView;
@property(nonatomic,strong) NSArray *placeHolderList;
@end

@implementation CustomDiviceView

- (instancetype)initWithFrame:(CGRect)frame{
    if (self = [super initWithFrame:frame]) {
       
        self.backgroundColor = [[UIColor orangeColor] colorWithAlphaComponent:0.5];
        [self addSubview:self.itemCollectionView];
        [self.itemCollectionView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.edges.equalTo(self);
        }];
        [RACObserve(self, categoryItemList) subscribeNext:^(id x) {
           
            [self.itemCollectionView reloadData];
            
        }];
        self.placeHolderList = @[@"sy_zfw",@"sy_zr",@"sy_zhd",@"sy_zgz",@"sy_zzf",@"sy_xjn",@"sy_xsj",@"sy_qb"];
    }
    return self;
}

-(UICollectionView *)itemCollectionView{

    if (_itemCollectionView == nil) {
        UICollectionViewFlowLayout *layout = [[UICollectionViewFlowLayout alloc]init];
        layout.itemSize = CGSizeMake(kScreenWidth/4.04, kScreenWidth/4.04);
        layout.minimumLineSpacing = 1;
        layout.minimumInteritemSpacing = 1;
        _itemCollectionView = [[UICollectionView alloc]initWithFrame:(CGRectZero) collectionViewLayout:layout];
        _itemCollectionView.backgroundColor = [UIColor whiteColor];
        _itemCollectionView.dataSource = self;
        _itemCollectionView.delegate = self;
        [_itemCollectionView registerClass:[CHCategoryCollectionViewCell class] forCellWithReuseIdentifier:@"itemCell"];
    }
    
    return _itemCollectionView;
}

-(NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section{

    return  self.categoryItemList.count;
}

-(UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath{
    CHCategoryCollectionViewCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"itemCell" forIndexPath:indexPath];
    NSDictionary *dic = self.categoryItemList[indexPath.row];
    CHCategoryItemModel *model = [[CHCategoryItemModel alloc]init];
    model.name = [dic objectForKey:@"item_name"];
    model.iconUrl = [dic objectForKey:@"iconimage_url"];
    model.placeHolder = self.placeHolderList[indexPath.row];
    cell.itemModel = model;
    
//    cell.backgroundColor = [UIColor orangeColor];
    return cell;
}

@end
