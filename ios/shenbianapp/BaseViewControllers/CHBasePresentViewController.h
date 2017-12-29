//
//  CHBasePresentViewController.h
//  shenbianapp
//
//  Created by book on 2017/8/20.
//  Copyright © 2017  . All rights reserved.
//

#import <UIKit/UIKit.h>

@interface CHBasePresentViewController : BaseViewController
@property(nonatomic,strong) LXButton *lefgtButton;
@property(nonatomic,strong) LXButton *rightTopButton;
@property(nonatomic,strong)UIView *topView;

- (void)clickRightTopButton:(UIButton*)button;
@end
