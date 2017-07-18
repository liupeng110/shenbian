//
//  FocusRecommentTableViewCell.h
//  shenbianapp
//
//  Created by 杨绍智 on 17/7/15.
//  Copyright © 2017年 杨绍智. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface FocusRecommentTableViewCell : UITableViewCell<UICollectionViewDataSource,UICollectionViewDelegateFlowLayout>
@property (nonatomic,strong)UIView * recommentContentView;
@property (nonatomic,strong)UILabel * titleName;
@property (nonatomic,strong)UICollectionView * recomentCollecttion;
@end
