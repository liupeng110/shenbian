//
//  CHCardsPanelView.m
//  shenbianapp
//
//  Created by book on 2017/8/19.
//  Copyright © 2017年 helinkeji. All rights reserved.
//

#import "CHCardsPanelView.h"
@interface CHCardsPanelView ()<UICollectionViewDelegate,UICollectionViewDataSource>
@property(nonatomic,strong) UIView *customTopNavView;
@property(nonatomic,strong) NSArray *secondKindList;
@property(nonatomic,strong) UICollectionView *cardsCollectionView;
@end

@implementation CHCardsPanelView

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

-(instancetype)initWithFrame:(CGRect)frame{

    if (self = [super initWithFrame:frame]) {

        self.secondKindList = self.pannelModel.secondKindList;
    }
    return self;
}

-(UIView *)customTopNavView{
    if (_customTopNavView == nil) {
        _customTopNavView = [UIView new];
        _customTopNavView.backgroundColor = [UIColor colorWithHexColor:@"FF6600"];
        for (NSString *name in self.secondKindList) {
            UILabel *label = [UILabel new];
            label.text = name;
            [_customTopNavView addSubview:label];
            
        }
    }
    return _customTopNavView;

}

-(UICollectionView *)cardsCollectionView{
    if (_cardsCollectionView == nil) {
        UICollectionViewFlowLayout *layout = [[UICollectionViewFlowLayout alloc]init];
        layout.itemSize = CGSizeMake(kScreenWidth/3, 150);
        layout.minimumLineSpacing = 5;
        layout.minimumInteritemSpacing = 5;
        
        _cardsCollectionView = [[UICollectionView alloc]initWithFrame:(CGRectZero) collectionViewLayout:layout];
        _cardsCollectionView.dataSource = self;
        _cardsCollectionView.delegate = self;
        [_cardsCollectionView registerClass:[UICollectionViewCell class] forCellWithReuseIdentifier:@"cardCell"];
        
    }
    return _cardsCollectionView;
}

#pragma  -Mark collectionDelegate .....
-(NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section{

   return  self.secondKindList.count;
}

-(UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath{
    UICollectionViewCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"cardCell" forIndexPath:indexPath];
    cell.backgroundColor = [UIColor purpleColor];
    return cell;
}
@end
