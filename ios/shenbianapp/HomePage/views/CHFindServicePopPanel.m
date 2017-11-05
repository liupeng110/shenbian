//
//  CHFindServicePopPanel.m
//  shenbianapp
//
//  Created by book on 2017/11/3.
//  Copyright © 2017年 helinkeji. All rights reserved.
//

#import "CHFindServicePopPanel.h"

@interface CHFindServicePopPanel ()<UICollectionViewDelegate,UICollectionViewDataSource>
@property(nonatomic,strong)UICollectionView *categoryCollectionView;
@property(nonatomic,assign)BOOL isFirstTime;
@end

@implementation CHFindServicePopPanel

-(instancetype)initWithFrame:(CGRect)frame{

    if (self = [super initWithFrame:frame]) {
        [self addSubview:self.categoryCollectionView];
        [self.categoryCollectionView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(self).offset(20);
            make.bottom.equalTo(self);
            make.left.equalTo(self).offset(15);
            make.right.equalTo(self).offset(-15);
        }];
    }
    return self;
}

-(UICollectionView *)categoryCollectionView{
    if (_categoryCollectionView == nil) {
        UICollectionViewFlowLayout *layout = [[UICollectionViewFlowLayout alloc]init];
        layout.minimumInteritemSpacing = 10;
        layout.minimumInteritemSpacing = 10;
        layout.itemSize = CGSizeMake(100, 30);
        _categoryCollectionView = [[UICollectionView alloc]initWithFrame:(CGRectZero) collectionViewLayout:layout];
        _categoryCollectionView.delegate =  self;
        _categoryCollectionView.dataSource = self;
        [_categoryCollectionView registerClass:[UICollectionViewCell class] forCellWithReuseIdentifier:@"categoryCell"];
        _categoryCollectionView.backgroundColor = [UIColor clearColor];
        _categoryCollectionView.scrollEnabled = NO;
    }
    return _categoryCollectionView;

}


-(NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section{

   return self.panelNameList.count;
}

-(UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath{
    UICollectionViewCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"categoryCell" forIndexPath:indexPath];
    UILabel *label = cell.contentView.subviews.firstObject;
    if (label == nil) {
        label = [UILabel new];
        label.frame = cell.bounds;
        label.font = [UIFont systemFontOfSize:15];
        label.textColor = [UIColor colorWithHexString:@"#4f5965"];
        label.textAlignment = NSTextAlignmentCenter;
        label.layer.borderColor = [UIColor lightGrayColor].CGColor;
        label.layer.borderWidth = 1;
        [cell.contentView addSubview:label];
        
    }
    label.text = self.panelNameList[indexPath.row];
    if (!self.isFirstTime && indexPath.row == 0) {
        label.textColor = [UIColor colorWithHexString:@"#009698"];
        
        self.isFirstTime = YES;
    }
    return cell;
}
-(void)collectionView:(UICollectionView *)collectionView didSelectItemAtIndexPath:(NSIndexPath *)indexPath{
    UICollectionViewCell *cell = [collectionView cellForItemAtIndexPath:indexPath];
    
    for (UICollectionViewCell *cell in collectionView.visibleCells) {
        UILabel *label = cell.contentView.subviews.firstObject;
        label.textColor = [UIColor colorWithHexString:@"#4f5965"];
    }
    UILabel *label = cell.contentView.subviews.firstObject;
    label.textColor = [UIColor colorWithHexString:@"#009698"];
    
}
@end
