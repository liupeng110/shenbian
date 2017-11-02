//
//  CHKindView.m
//  shenbianapp
//
//  Created by book on 2017/9/2.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import "CHOverbalanceView.h"
#import "CHOverbalanceCollectionViewCell.h"
@interface CHOverbalanceView ()<UICollectionViewDelegate,UICollectionViewDataSource>
@property(nonatomic,strong)UICollectionView *kindCollectionView;
@property(nonatomic,strong)NSArray *placeHolderList;
@property(nonatomic,strong) UILabel *overBalanceLabel;
@property(nonatomic,strong) UILabel *seeAllLabel;

@end

@implementation CHOverbalanceView

-(instancetype)initWithFrame:(CGRect)frame{
    if (self = [super initWithFrame:frame]) {
        self.clipsToBounds = YES;
        [self addSubview:self.overBalanceLabel];
        [self.overBalanceLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.left.equalTo(self).offset(0);
            make.width.mas_equalTo(40);
            make.height.mas_equalTo(20);
        }];
        
        [self addSubview:self.seeAllLabel];
        [self.seeAllLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(self).offset(0);
            make.right.equalTo(self).offset(0);
            make.width.mas_equalTo(60);
            make.height.mas_equalTo(20);
        }];
        
        
        [self addSubview:self.kindCollectionView];
        [self.kindCollectionView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.right.bottom.equalTo(self);
            make.top.equalTo(self).offset(30);
        }];
        
        self.placeHolderList = @[@"leftHolder",@"rightHolder"];
        self.backgroundColor = [UIColor whiteColor];
        @weakify(self);
        [RACObserve(self, overBablanceList) subscribeNext:^(id x) {
            @strongify(self);
            [self.kindCollectionView reloadData];
        }];
    }
    return self;
}

-(UILabel *)overBalanceLabel{
    if (_overBalanceLabel == nil) {
        _overBalanceLabel = [[UILabel alloc]init];
        _overBalanceLabel.text = @"超值";
        _overBalanceLabel.font = [UIFont fontWithName:@"PingFangSC-Regular" size:15];
    }
    return _overBalanceLabel;
}

-(UILabel *)seeAllLabel{
    if (_seeAllLabel == nil) {
        _seeAllLabel = [[UILabel alloc]init];
        _seeAllLabel.text = @"查看全部";
        _seeAllLabel.textAlignment = NSTextAlignmentCenter;
        _seeAllLabel.font = [UIFont fontWithName:@"PingFangSC-Regular" size:15];
        _seeAllLabel.textColor = [UIColor colorWithHexColor:@"#009698"];
    }
    return _seeAllLabel;
}

-(UICollectionView *)kindCollectionView{

    if (_kindCollectionView == nil) {
        UICollectionViewFlowLayout *layout = [[UICollectionViewFlowLayout alloc]init];
        layout.itemSize = CGSizeMake(kScreenWidth/2.3, kScreenWidth/2.3);
        layout.minimumLineSpacing = 0;
        _kindCollectionView = [[UICollectionView alloc]initWithFrame:(CGRectZero) collectionViewLayout:layout];
        _kindCollectionView.backgroundColor = [UIColor whiteColor];
        [_kindCollectionView registerClass:[CHOverbalanceCollectionViewCell class] forCellWithReuseIdentifier:@"kindCell"];
        _kindCollectionView.delegate = self;
        _kindCollectionView.dataSource = self;
    }
    return _kindCollectionView;
}

-(NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section{

    return self.overBablanceList.count;
}

-(UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath{
   
    CHOverbalanceCollectionViewCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"kindCell" forIndexPath:indexPath];
    NSDictionary *dic = self.overBablanceList[indexPath.row];
    CHOverbalanceModel *model = [[CHOverbalanceModel alloc]init];
    model.coverUrl = [dic objectForKey:@"cover_url"];
    model.placeHolder = self.placeHolderList[indexPath.row];
    cell.model = model;
    
    return cell;
}
@end
