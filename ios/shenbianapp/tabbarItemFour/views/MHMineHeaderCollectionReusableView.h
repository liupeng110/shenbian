//
//  MHMineHeaderCollectionReusableView.h
//  Miaohi
//
//  Created by 沈开洋 on 16/11/29.
//  Copyright © 2016年 haiqiu. All rights reserved.
//

#import <UIKit/UIKit.h>



//@protocol MHMineHeaderCollectionReusableViewDelegate <NSObject>

//- (void)clickMineCentreHeaderViewWith:(SLButton *)sender;
//- (void)clickMineCentreattionAndFnasWith:(LXButton *)sender;
//@end
@interface MHMineHeaderCollectionReusableView : UICollectionReusableView
@property (strong, nonatomic) IBOutlet UIImageView *mhBackImgView;
@property (strong, nonatomic) IBOutlet UIImageView *mhUserHeaderImgView;
@property (strong, nonatomic) IBOutlet UIImageView *mhDavImgView;
@property (strong, nonatomic) IBOutlet UILabel *mhUserNameLabel;
@property (strong, nonatomic) IBOutlet UIImageView *mhUserGerdenImgView;
@property (strong, nonatomic) IBOutlet UILabel *mhUserNoteLabel;//大咖描述
@property (weak, nonatomic) IBOutlet UIView *focusAndFansView;
@property (weak, nonatomic) IBOutlet UIButton *editAndAttionBtn;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *BottomHeigh;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *focusAndAttionBtnH;

@property (nonatomic,strong)LXButton * fansBtn;
@property (nonatomic,strong)LXButton * attentionsBtn;
//@property (weak, nonatomic) id<MHMineHeaderCollectionReusableViewDelegate>delegate;
//- (void)creatMhHeaderViewithUserInfoModel:(PersonsetInfoModel *)personModel VieWith:(BOOL)isMyHomePage;
@end
