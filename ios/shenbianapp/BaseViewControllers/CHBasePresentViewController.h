//
//  CHBasePresentViewController.h
//  shenbianapp
//
//  Created by book on 2017/8/20.
//  Copyright © 2017年 杨绍智. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface CHBasePresentViewController : UIViewController
@property(nonatomic,strong) LXButton *closeBtn;
@property(nonatomic,strong) LXButton *rightTopButton;

- (void)clickRightTopButton:(UIButton*)button;
@end
