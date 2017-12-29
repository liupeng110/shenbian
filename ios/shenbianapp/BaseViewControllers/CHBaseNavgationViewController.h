//
//  CHBaseNavgationViewController.h
//  shenbianapp
//
//  Created by book on 2017/8/20.
//  Copyright © 2017  . All rights reserved.
//

#import <UIKit/UIKit.h>

@interface CHBaseNavgationViewController : BaseViewController

@property(nonatomic,strong)UIButton *rightButton;
@property(nonatomic,strong)UIButton *backButton;
- (void)clickBackButton;
- (void)clickRightButton;
@end
