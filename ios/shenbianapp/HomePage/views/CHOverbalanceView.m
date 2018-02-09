//
//  CHKindView.m
//  shenbianapp
//
//  Created by book on 2017/9/2.
//  Copyright © 2017 . All rights reserved.
//

#import "CHOverbalanceView.h"
#import "CHOverbalanceCollectionViewCell.h"
@interface CHOverbalanceView ()<UICollectionViewDelegate,UICollectionViewDataSource>
@property(nonatomic,strong)UICollectionView *kindCollectionView;
@property(nonatomic,strong)NSArray *placeHolderList;
@property(nonatomic,strong) UILabel *overBalanceLabel;
@property(nonatomic,strong) UIButton *seeAllButton;

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
        
        [self addSubview:self.seeAllButton];
        [self.seeAllButton mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(self).offset(0);
            make.right.equalTo(self).offset(0);
            make.width.mas_equalTo(70);
            make.height.mas_equalTo(20);
        }];
        
        [self insertSubview:self.kindCollectionView atIndex:0];
        [self.kindCollectionView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.right.bottom.equalTo(self);
            make.top.equalTo(self.overBalanceLabel.mas_bottom);
        }];
        
        self.placeHolderList = @[@"default_cover",@"default_cover"];
        self.backgroundColor = [UIColor whiteColor];
        @weakify(self);
        [RACObserve(self,overBablanceList) subscribeNext:^(id x) {
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

-(UIButton *)seeAllButton{
    if (_seeAllButton == nil) {
        _seeAllButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_seeAllButton setTitle:@"查看全部" forState:(UIControlStateNormal)];
        [_seeAllButton.titleLabel setFont:[UIFont systemFontOfSize:15]];
        [_seeAllButton setTitleColor:[UIColor colorWithHexColor:@"#009698"] forState:(UIControlStateNormal)];
        [_seeAllButton addTarget:self action:@selector(clickSeeAllButton) forControlEvents:(UIControlEventTouchUpInside)];
    }
    return _seeAllButton;
}

- (void)clickSeeAllButton{
    if (self.seeAllCategory) {
        self.seeAllCategory();
    }
}

-(UICollectionView *)kindCollectionView{

    if (_kindCollectionView == nil) {
        UICollectionViewFlowLayout *layout = [[UICollectionViewFlowLayout alloc]init];
        layout.itemSize = CGSizeMake(kScreenWidth/2.4 , kScreenWidth/2.4);
        layout.minimumLineSpacing = 30;
        layout.scrollDirection = UICollectionViewScrollDirectionHorizontal;
        _kindCollectionView = [[UICollectionView alloc]initWithFrame:(CGRectZero) collectionViewLayout:layout];
        _kindCollectionView.backgroundColor = [UIColor whiteColor];
        [_kindCollectionView registerClass:[CHOverbalanceCollectionViewCell class] forCellWithReuseIdentifier:@"kindCell"];
        _kindCollectionView.delegate = self;
        _kindCollectionView.dataSource = self;
//        _kindCollectionView.scrollEnabled = NO;
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
    model.coverUrl = [dic objectForKey:@"homeUrl"];
    model.placeHolder = self.placeHolderList[indexPath.row];
    cell.model = model;
   
    return cell;
}

-(void)collectionView:(UICollectionView *)collectionView didSelectItemAtIndexPath:(NSIndexPath *)indexPath{

    if (self.tapInOverBalence) {
        NSDictionary *dic = self.overBablanceList[indexPath.row];
        NSString *serviceId = [dic objectForKey:@"id"];
        if (serviceId) {
            self.tapInOverBalence([dic objectForKey:@"id"]);
        }
    }
    
}

@end
