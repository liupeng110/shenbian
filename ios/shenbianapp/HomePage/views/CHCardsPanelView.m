//
//  CHCardsPanelView.m
//  shenbianapp
//
//  Created by book on 2017/8/19.
//  Copyright © 2017 helinkeji. All rights reserved.
//

#import "CHCardsPanelView.h"
#import "CHCategoryCollectionViewCell.h"
#import "CHCategoryItemModel.h"



@interface CHCardsPanelView ()<UICollectionViewDelegate,UICollectionViewDataSource>

@property(nonatomic,strong) NSArray *placeHolderList;
@property(nonatomic,strong) UICollectionView *itemCollectionView;

@end

@implementation CHCardsPanelView

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
        layout.itemSize = CGSizeMake(kScreenWidth/4.05, kScreenWidth/4.05);
        layout.minimumLineSpacing = 1;
        layout.minimumInteritemSpacing = 1;
        layout.scrollDirection = UICollectionViewScrollDirectionVertical;
        _itemCollectionView = [[UICollectionView alloc]initWithFrame:(CGRectZero) collectionViewLayout:layout];
        _itemCollectionView.backgroundColor = [UIColor whiteColor];
        _itemCollectionView.dataSource = self;
        _itemCollectionView.delegate = self;
        [_itemCollectionView registerClass:[CHCategoryCollectionViewCell class] forCellWithReuseIdentifier:@"itemCell"];
        _itemCollectionView.scrollEnabled = NO;
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
    model.name = [dic objectForKey:@"text"];
    model.iconUrl = [dic objectForKey:@"url"];
    model.placeHolder = self.placeHolderList[indexPath.row];
    cell.itemModel = model;
    
    return cell;
}

-(void)collectionView:(UICollectionView *)collectionView didSelectItemAtIndexPath:(NSIndexPath *)indexPath{
    self.selectFindType(indexPath.row);
}
@end
