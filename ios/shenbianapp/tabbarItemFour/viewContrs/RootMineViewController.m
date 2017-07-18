//
//  RootMineViewController.m
//  shenbianapp
//
//  Created by 杨绍智 on 17/7/12.
//  Copyright © 2017年 杨绍智. All rights reserved.
//

#import "RootMineViewController.h"
#import "MHCollectionViewFlowLayout.h"
#import "MHMineHeaderCollectionReusableView.h"
#import "MineCollectionViewCell.h"
#import "CellSPaceCollectionReusableView.h"
@interface RootMineViewController ()<UICollectionViewDataSource, UICollectionViewDelegate,UICollectionViewDelegateFlowLayout>
@property (nonatomic,strong)UICollectionView * collectionView;
@property (nonatomic,strong)MHMineHeaderCollectionReusableView *headView;
@end

@implementation RootMineViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor whiteColor];
    self.automaticallyAdjustsScrollViewInsets = NO;
    self.navigationController.navigationBar.hidden = YES;
    MHCollectionViewFlowLayout * stretchyLayout = [[MHCollectionViewFlowLayout alloc] init];
    stretchyLayout.sectionInset = UIEdgeInsetsMake(0, 0, 0, 0);
    stretchyLayout.headerReferenceSize = CGSizeMake(ScreenWidth, 260);
    self.collectionView = [[UICollectionView alloc] initWithFrame:CGRectMake(0, 0, ScreenWidth, ScreenHeight-49) collectionViewLayout:stretchyLayout];
    self.collectionView.backgroundColor = [UIColor whiteColor];
    self.collectionView.alwaysBounceVertical = YES;
    self.collectionView.showsVerticalScrollIndicator = NO;
    self.collectionView.dataSource = self;
    self.collectionView.delegate = self;
    
    [self.view addSubview:self.collectionView];
    [self.collectionView registerNib:[UINib nibWithNibName:@"MHMineHeaderCollectionReusableView" bundle:nil] forSupplementaryViewOfKind:UICollectionElementKindSectionHeader withReuseIdentifier:@"Header"];
    [self.collectionView registerNib:[UINib nibWithNibName:@"CellSPaceCollectionReusableView" bundle:nil] forSupplementaryViewOfKind:UICollectionElementKindSectionHeader withReuseIdentifier:@"cellspace"];
    [self.collectionView registerNib:[UINib nibWithNibName:@"MineCollectionViewCell" bundle:nil] forCellWithReuseIdentifier:@"mine"];

}

- (NSInteger)numberOfSectionsInCollectionView:(UICollectionView *)collectionView{
    return 2;
}

- (NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section{
    if (section==0) {
       return 4;
    }else{
      return 2;
    }
    
}

- (CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout*)collectionViewLayout referenceSizeForHeaderInSection:(NSInteger)section{
    if (section== 0) {
        return  CGSizeMake(ScreenWidth, 260);
        
    }else{
        return CGSizeMake(0, 10);
    }
}

- (CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout *)collectionViewLayout referenceSizeForFooterInSection:(NSInteger)section{
   return CGSizeMake(0, 0);
}

- (CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout *)collectionViewLayout sizeForItemAtIndexPath:(NSIndexPath *)indexPath{
    return CGSizeMake(ScreenWidth, 50);
}

- (UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath{
    MineCollectionViewCell * setCell = [collectionView dequeueReusableCellWithReuseIdentifier:@"mine" forIndexPath:indexPath];
    if (indexPath.section == 0){
        if (indexPath.row == 0) {
            setCell.titleName.text = @"消息通知";
        }else if (indexPath.row == 1){
            setCell.titleName.text = @"我的订单";
        }else if (indexPath.row == 2){
            setCell.titleName.text = @"我的收藏";
        }else{
            setCell.titleName.text = @"我的地址";
        }
    }else if (indexPath.section == 1){
        if (indexPath.row == 0) {
            setCell.titleName.text = @"帮助";
        }else{
            setCell.titleName.text = @"反馈";
        }
    }
    return setCell;
}

- (UICollectionReusableView *)collectionView:(UICollectionView *)collectionView viewForSupplementaryElementOfKind:(NSString *)kind atIndexPath:(NSIndexPath *)indexPath{
    if (kind == UICollectionElementKindSectionHeader) {
        if (indexPath.section == 0) {
            self.headView = [collectionView dequeueReusableSupplementaryViewOfKind:kind withReuseIdentifier:@"Header" forIndexPath:indexPath];
            //self.headView.delegate = self;
            //如果是登录用户的默认
            return self.headView;
        }else{
            CellSPaceCollectionReusableView * view = [collectionView dequeueReusableSupplementaryViewOfKind:kind withReuseIdentifier:@"cellspace" forIndexPath:indexPath];
            return view;
        }
    }
    return nil;
}

- (UIStatusBarStyle)preferredStatusBarStyle{
    return UIStatusBarStyleLightContent;
}
- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
}

@end
