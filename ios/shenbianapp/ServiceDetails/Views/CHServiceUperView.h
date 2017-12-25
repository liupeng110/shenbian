//
//  CHServiceUperView.h
//  shenbianapp
//
//  Created by book on 2017/9/14.
//  Copyright © 2017 . All rights reserved.
//

#import <UIKit/UIKit.h>

#import "CHServiceTableViewCell.h"
#import "CHServiceDetailModel.h"
@interface CHServiceUperView : UIView
@property(nonatomic,strong)UIButton *backButton;
@property(nonatomic,copy)ClickAddShopCart clickAddShopCart ;
@property(nonatomic,strong)CHServiceDetailModel *model;

@end
