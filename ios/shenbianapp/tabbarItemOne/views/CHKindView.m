//
//  CHKindView.m
//  shenbianapp
//
//  Created by book on 2017/9/2.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import "CHKindView.h"
#import "CHKindCollectionViewCell.h"
@interface CHKindView ()<UICollectionViewDelegate,UICollectionViewDataSource>
@property(nonatomic,strong)UICollectionView *kindCollectionView;
@property(nonatomic,strong)NSArray *placeHolderList;

@end

@implementation CHKindView

-(instancetype)initWithFrame:(CGRect)frame{
    if (self = [super initWithFrame:frame]) {
        [self addSubview:self.kindCollectionView];
        [self.kindCollectionView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.edges.equalTo(self);
        }];
        
        self.placeHolderList = @[@"",@""];
    }
    return self;
}

-(UICollectionView *)kindCollectionView{

    if (_kindCollectionView == nil) {
        UICollectionViewFlowLayout *layout = [[UICollectionViewFlowLayout alloc]init];
        layout.itemSize = CGSizeMake(kScreenWidth/2, kScreenWidth/2);
        layout.minimumLineSpacing = 0;
        _kindCollectionView = [[UICollectionView alloc]initWithFrame:(CGRectZero) collectionViewLayout:layout];
        _kindCollectionView.backgroundColor = [UIColor whiteColor];
        [_kindCollectionView registerClass:[CHKindCollectionViewCell class] forCellWithReuseIdentifier:@"kindCell"];
    }
    return _kindCollectionView;
}

-(NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section{

    return self.kindList.count;
}

-(UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath{
   
    CHKindCollectionViewCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"kindCell" forIndexPath:indexPath];
    NSDictionary *dic = self.kindList[indexPath.row];
    CHKindModel *model = [[CHKindModel alloc]init];
    model.coverUrl = [dic objectForKey:@"cover_url"];
    model.placeHolder = self.placeHolderList[indexPath.row];
    cell.model = model;
    
    return cell;
}
@end
