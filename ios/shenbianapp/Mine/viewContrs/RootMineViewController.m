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

#import "CHArticleDetailsViewController.h"
#import "CHServiceDetailsViewController.h"

#import "CHArticleAndServiceListViewController.h"
#import "CHStoreInfoViewController.h"
@interface RootMineViewController ()<UICollectionViewDataSource, UICollectionViewDelegate,UICollectionViewDelegateFlowLayout>
@property (nonatomic,strong)UICollectionView * collectionView;
@property (nonatomic,strong)MHMineHeaderCollectionReusableView *headView;
@property (nonatomic,copy) ClickMyService clickMyService;
@property (nonatomic,copy) ClickMyArticle clickMyArticle;
@end

@implementation RootMineViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor lightGrayColor];
    self.automaticallyAdjustsScrollViewInsets = NO;
    MHCollectionViewFlowLayout * stretchyLayout = [[MHCollectionViewFlowLayout alloc] init];
    stretchyLayout.sectionInset = UIEdgeInsetsMake(0, 0, 0, 0);
    stretchyLayout.headerReferenceSize = CGSizeMake(kScreenWidth, 260);
    self.collectionView = [[UICollectionView alloc] initWithFrame:CGRectMake(0, 0, kScreenWidth, kScreenHeight-49) collectionViewLayout:stretchyLayout];
    self.collectionView.backgroundColor = [UIColor whiteColor];
    self.collectionView.alwaysBounceVertical = YES;
    self.collectionView.showsVerticalScrollIndicator = NO;
    self.collectionView.dataSource = self;
    self.collectionView.delegate = self;
    
    [self.view addSubview:self.collectionView];
    [self.collectionView registerNib:[UINib nibWithNibName:@"MHMineHeaderCollectionReusableView" bundle:nil] forSupplementaryViewOfKind:UICollectionElementKindSectionHeader withReuseIdentifier:@"Header"];
    [self.collectionView registerNib:[UINib nibWithNibName:@"CellSPaceCollectionReusableView" bundle:nil] forSupplementaryViewOfKind:UICollectionElementKindSectionHeader withReuseIdentifier:@"cellspace"];
    [self.collectionView registerNib:[UINib nibWithNibName:@"MineCollectionViewCell" bundle:nil] forCellWithReuseIdentifier:@"mine"];

    @weakify(self);
    self.clickMyArticle = ^(){
        @strongify(self);
        CHArticleAndServiceListViewController *articleVC = [[CHArticleAndServiceListViewController alloc]init];
        articleVC.title = @"我的文章";
        [self.navigationController pushViewController:articleVC animated:YES];
    };
    
    self.clickMyService = ^{
        @strongify(self);
        CHArticleAndServiceListViewController *serviceDetail = [[CHArticleAndServiceListViewController alloc]init];
        serviceDetail.title = @"我的服务";
        [self.navigationController pushViewController:serviceDetail animated:YES];
    };
    
}

-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    self.navigationController.navigationBarHidden = YES;

}

-(void)viewWillDisappear:(BOOL)animated{
    [super viewWillDisappear:animated];
    self.navigationController.navigationBarHidden = NO;

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
        
        return  CGSizeMake(kScreenWidth, 260);
        
    } else {
        
        return CGSizeMake(0, 10);
    }
}

- (CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout *)collectionViewLayout referenceSizeForFooterInSection:(NSInteger)section{
   return CGSizeMake(0, 0);
}

- (CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout *)collectionViewLayout sizeForItemAtIndexPath:(NSIndexPath *)indexPath{
    return CGSizeMake(kScreenWidth, 50);
}

- (UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath{
    MineCollectionViewCell * setCell = [collectionView dequeueReusableCellWithReuseIdentifier:@"mine" forIndexPath:indexPath];
    if (indexPath.section == 0){
        if (indexPath.row == 0) {
            setCell.titleName.text = @"消息通知";
            setCell.iconImageView.image = [UIImage imageNamed:@"wd_xx"];
        }else if (indexPath.row == 1){
            setCell.titleName.text = @"我的收入";
            setCell.iconImageView.image = [UIImage imageNamed:@"wd_sr"];

        }else if (indexPath.row == 2){
            setCell.titleName.text = @"我的订单";
            setCell.iconImageView.image = [UIImage imageNamed:@"wd_dd"];

        } else if(indexPath.row == 3){
            setCell.titleName.text = @"资料设置";
            setCell.iconImageView.image = [UIImage imageNamed:@"wd_zl"];

        } else if (indexPath.row == 4){
            setCell.titleName.text = @"安全与隐私";
            setCell.iconImageView.image = [UIImage imageNamed:@"wd_aq"];

        }
        
    }else if (indexPath.section == 1){
        if (indexPath.row == 0) {
            setCell.titleName.text = @"反馈";
            setCell.iconImageView.image = [UIImage imageNamed:@"wd_fk"];

        }else{
            setCell.titleName.text = @"帮助";
            setCell.iconImageView.image = [UIImage imageNamed:@"wd_bz"];

        }
    }
    return setCell;
}

- (UICollectionReusableView *)collectionView:(UICollectionView *)collectionView viewForSupplementaryElementOfKind:(NSString *)kind atIndexPath:(NSIndexPath *)indexPath{
    if (kind == UICollectionElementKindSectionHeader) {
        if (indexPath.section == 0) {
            self.headView = [collectionView dequeueReusableSupplementaryViewOfKind:kind withReuseIdentifier:@"Header" forIndexPath:indexPath];
            self.headView.clickMyService = self.clickMyService;
            self.headView.clickMyArticle = self.clickMyArticle;
            //如果是登录用户的默认
            return self.headView;
        }else{
            CellSPaceCollectionReusableView * view = [collectionView dequeueReusableSupplementaryViewOfKind:kind withReuseIdentifier:@"cellspace" forIndexPath:indexPath];
        
            return view;
        }
    }
    return nil;
}

-(void)collectionView:(UICollectionView *)collectionView didSelectItemAtIndexPath:(NSIndexPath *)indexPath{

    if (indexPath.section == 0 && indexPath.row == 3) {
        CHStoreInfoViewController *storeInfoVC = [[CHStoreInfoViewController alloc]init];
        [self.navigationController pushViewController:storeInfoVC animated:YES];
    }
    
}

- (UIStatusBarStyle)preferredStatusBarStyle{
    return UIStatusBarStyleLightContent;
}
- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
}

@end
