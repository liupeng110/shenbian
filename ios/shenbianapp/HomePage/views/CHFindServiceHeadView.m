//
//  CHFindServiceHeadView.m
//  shenbianapp
//
//  Created by book on 2017/11/3.
//  Copyright © 2017年 helinkeji. All rights reserved.
//

#import "CHFindServiceHeadView.h"

@interface CHFindServiceHeadView ()<UICollectionViewDelegate,UICollectionViewDataSource,UICollectionViewDelegateFlowLayout>
@property(nonatomic,strong)UICollectionView *categoryCollection;
@property(nonatomic,assign)BOOL isfirstTime;

@end

@implementation CHFindServiceHeadView

-(instancetype)initWithFrame:(CGRect)frame{

    if (self = [super initWithFrame:frame]) {
        self.layer.borderWidth = 1;
        self.layer.borderColor = [UIColor colorWithHexString:@"#e2e6eb"].CGColor;
        [self addSubview:self.categoryCollection];
        [self.categoryCollection mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.bottom.equalTo(self);
            make.left.equalTo(self).offset(15);
            make.right.equalTo(self).offset(-45);
        }];
        
    }

    return self;
}


-(UICollectionView *)categoryCollection{

    if (_categoryCollection == nil) {
        UICollectionViewFlowLayout *layout = [[UICollectionViewFlowLayout alloc]init];
        layout.minimumInteritemSpacing = 20;
        layout.scrollDirection = UICollectionViewScrollDirectionHorizontal;

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
    return self.categoryList.count;
}

-(UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath{
    UICollectionViewCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"categoryCell" forIndexPath:indexPath];
    UILabel *label = cell.contentView.subviews.firstObject;
    if (label == nil) {
        label = [UILabel new];
        label.frame = cell.bounds;
        label.font = [UIFont systemFontOfSize:15];
        label.textColor = [UIColor colorWithHexString:@"#4f5965"];
        [cell.contentView addSubview:label];
        
        UILabel *line = [UILabel new];
        line.backgroundColor = [UIColor colorWithHexString:@"#009698"];
        line.hidden = YES;
        [label addSubview:line];
        [line mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.right.bottom.equalTo(label);
            make.height.mas_equalTo(2);
        }];
    }
    label.text = self.categoryList[indexPath.row];
    if (!self.isfirstTime && indexPath.row == 0) {
        label.textColor = [UIColor colorWithHexString:@"#009698"];
        UILabel *line  = label.subviews.firstObject;
        line.hidden = NO;
        self.isfirstTime = YES;
    }
    return cell;
}

-(void)collectionView:(UICollectionView *)collectionView didSelectItemAtIndexPath:(NSIndexPath *)indexPath{
    UICollectionViewCell *cell = [collectionView cellForItemAtIndexPath:indexPath];

    for (UICollectionViewCell *cell in collectionView.visibleCells) {
        UILabel *label = cell.contentView.subviews.firstObject;
        label.textColor = [UIColor colorWithHexString:@"#4f5965"];
        UILabel *line  = label.subviews.firstObject;
        line.hidden = YES;
    }
    UILabel *label = cell.contentView.subviews.firstObject;
    label.textColor = [UIColor colorWithHexString:@"#009698"];
    UILabel *line  = label.subviews.firstObject;
    line.hidden = NO;

}

-(CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout *)collectionViewLayout sizeForItemAtIndexPath:(NSIndexPath *)indexPath{
    NSString * text = self.categoryList[indexPath.row];
    CGSize size = [text sizeWithAttributes:@{NSFontAttributeName:[UIFont systemFontOfSize:15]}];
    return CGSizeMake(size.width + 1, self.height);
}

@end
